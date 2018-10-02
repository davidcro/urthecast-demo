package com.dcrowe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dcrowe.entity.Location;
import com.vividsolutions.jts.geom.Geometry;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

	public List<Location> findAll();

	@Query(value = "select l from Location l where within(l.geom, :boundingBox) = true")
	public List<Location> findAllInBoundingBox(Geometry boundingBox);
	
//	@Query(value = "select * from location where ST_Within(geom, :boundingBox) = true",
//			nativeQuery = true)
//	public List<Location> findAllInBoundingBox(Geometry boundingBox);
}
