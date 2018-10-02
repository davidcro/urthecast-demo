package com.dcrowe.service;

import java.util.List;

import com.dcrowe.entity.Location;

public interface LocationService {

	/**
	 * Retrieves list of locations based on country bounding box
	 * @param name - name of country
	 * @return list of locations
	 * @throws Exception
	 */
	List<Location> getLocationsByCountry(String name) throws Exception;

	/**
	 * Adds a new location to the database
	 * @param location - location to be saved
	 * @return success/failure
	 * @throws Exception
	 */
	Boolean addLocation(Location location) throws Exception;

}