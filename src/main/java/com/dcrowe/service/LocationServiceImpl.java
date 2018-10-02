package com.dcrowe.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcrowe.entity.BoundingBox;
import com.dcrowe.entity.Location;
import com.dcrowe.repository.LocationRepository;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
/**
 * Location service class
 * @author davidcrowe
 *
 */
@Service
public class LocationServiceImpl implements LocationService {
	
	private static final String CANADA = "CANADA";
	private static final String UK = "UK";
	private static final String FRANCE = "FRANCE";
	private static final String IRELAND = "IRELAND";
	
	private static Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);
	
	@Autowired
	LocationRepository locationRepository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Location> getLocationsByCountry(String name) throws Exception {
		
		Geometry boundingBox = getBoundingBox(name);
		
		if (boundingBox == null ) {
			logger.info(String.format("No bounding box available for country %s", name));
			return new ArrayList<Location>();
		}
		
		List<Location> locations = locationRepository.findAllInBoundingBox(boundingBox);
		
		writeLocationWkt(locations);
		
		return locations;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean addLocation(Location location) throws Exception {
		
		try {
			location.setGeom(convertWktToGeometry(location.getGeomWkt()));
			Location result = locationRepository.save(location);
			
			return result == null ? false : true;
		} catch (Exception e) {
			logger.error("Unable to parse WKT into Geometry");
			throw new Exception(e);
		}
		
	}

	/**
	 * Gets correct bounding box geometry based on bounding box enum
	 * @param name
	 * @return
	 * @throws Exception
	 */
	
	//TODO - rework this - database?
	private Geometry getBoundingBox(String name) throws Exception {
		
		String boundingBoxWkt;
		
		switch (name.toUpperCase()) {
			case IRELAND:
				boundingBoxWkt = BoundingBox.IRELAND.getBoundingBoxWkt();
				break;
			case FRANCE:
				boundingBoxWkt = BoundingBox.FRANCE.getBoundingBoxWkt();
				break;
			case UK:
				boundingBoxWkt = BoundingBox.UK.getBoundingBoxWkt();
				break;
			case CANADA:
				boundingBoxWkt = BoundingBox.CANADA.getBoundingBoxWkt();
				break;
			default:
				return null;
		}
		
		try {
			return convertWktToGeometry(boundingBoxWkt);
		} catch (ParseException e) {
			logger.error("Unable to parse WKT into Geometry");
			throw new Exception(e);
		}
	}
	
	/**
	 * Converts WKT into Geometry
	 * @param wktGeom - WKT to be parsed
	 * @return Geometry
	 * @throws Exception 
	 */
	private Geometry convertWktToGeometry(String wktGeom) throws Exception {
		WKTReader reader = new WKTReader();
		Geometry geom;
		
		if (StringUtils.isNotEmpty(wktGeom)) {
				geom = reader.read(wktGeom);
				return geom;
		} else {
			logger.error("WKT is empty... aborting");
			throw new Exception();
		}
		
	}
	
	/**
	 * Sets GeomWkt for each location by converting geom to WKT
	 * @param locations - list of Locations
	 */
	private void writeLocationWkt(List<Location> locations) {
		
		WKTWriter writer = new WKTWriter();
		locations.forEach(location -> location.setGeomWkt(writer.write(location.getGeom())));
		
	}
}