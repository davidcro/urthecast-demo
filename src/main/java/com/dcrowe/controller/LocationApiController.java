package com.dcrowe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dcrowe.entity.Location;
import com.dcrowe.service.LocationService;
/**
 * Controller to handle RESTful API calls
 * @author dcrowe
 *
 */
@RestController
public class LocationApiController {

	private static Logger logger = LoggerFactory.getLogger(LocationApiController.class);
	
	@Autowired
	private LocationService locationService;
	
	/**
	 * Returns json response with all locations in a provided country
	 * @param country - identifies which country to retrieve locations from
	 * @return - json list of locations 
	 */
	@GetMapping("/locations/{country}")
	public ResponseEntity<?> retrieveLocationsByCountry(@PathVariable("country") String country) {
		
		logger.info(String.format("retrieveLocationsByCountry called with input: %s", country));
		
		List<Location> locations;
		try {
			locations = locationService.getLocationsByCountry(country);
			return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("There was a problem retrieving locations", HttpStatus.OK);
		}
		
		
	}
	
	/**
	 * Adds a new location to the database
	 * @param location - location to be saved
	 * @return success/failure message
	 */
	@PostMapping("/locations")
	public ResponseEntity<?> addLocation(@RequestBody Location location) {
		
		logger.info(String.format("addLocation called with input: %s", location.toString()));
		
		Boolean result;
		try {
			result = locationService.addLocation(location);
			if (result == true) {
				return new ResponseEntity<String>("Location saved successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("There was a problem saving the location", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<String>("There was a problem saving the location", HttpStatus.OK);
		}
		
	}
}
