package com.dcrowe.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.dcrowe.entity.Location;
import com.dcrowe.repository.LocationRepository;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

/**
 * Test class for LocationServiceImpl
 * @author dcrowe
 *
 */
public class LocationServiceImplTest {

	private LocationRepository locationRepository;
	
	private LocationService locationService;
	
	@Before
	public void setup() {
		locationService = new LocationServiceImpl();
		locationRepository = Mockito.mock(LocationRepository.class);
		ReflectionTestUtils.setField(locationService, "locationRepository", locationRepository);
	}
	
	@Test
	public void getLocationsByCountryTest() {
		
		// TODO extract dummy data creation out to private method
		List<Location> sampleResults = new ArrayList<>();
		Location dummyResult = new Location(new Long(1), "EDMONTON", "POINT (-113.323975 53.631611)");
		WKTReader reader =  new WKTReader();
		try {
			dummyResult.setGeom(reader.read(dummyResult.getGeomWkt()));
		} catch (ParseException e1) {
			fail("Unable to create geometry");
		}
		sampleResults.add(dummyResult);

		
		locationRepository = Mockito.mock(LocationRepository.class);
		ReflectionTestUtils.setField(locationService, "locationRepository", locationRepository);
		
		Mockito.when(locationRepository.findAllInBoundingBox(Mockito.anyObject())).thenReturn(sampleResults);
		
		
		//1 - Provided country that is not in our enum list should return empty list
		try {
			List<Location> results = locationService.getLocationsByCountry("USA");
			assert(results.isEmpty());
		} catch (Exception e) {
			fail("Unexpected exception thrown");
		}
		
		//2 - Provided country that is in our enum list should return one result
		try {
			List<Location> results = locationService.getLocationsByCountry("CANADA");
			assert(results.size() == 1);
		} catch (Exception e) {
			fail("Unexpected exception thrown");
		}
		
	}
	
	@Test(expected = Exception.class)
	public void addLocationTest() throws Exception {
		
		Location input = new Location(new Long(1), "EDMONTON", "POINT (-113.323975 53.631611)");
		
		//1) empty WKT should throw exception
		input.setGeomWkt("");
		
		locationService.addLocation(input);
		
	}

}