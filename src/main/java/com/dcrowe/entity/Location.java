package com.dcrowe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Geometry;

/**
 * Definition of Location entity
 * @author dcrowe
 *
 */

@Entity
@Table(name="location")
public class Location {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@JsonIgnore
	private Geometry geom;
	
	@Transient
	private String geomWkt;
	
	public Location()
	{}
	
	public Location(Long id, String name, String geomWkt) {
		
		this.id = id;
		this.name = name;
		this.geomWkt = geomWkt;
	}
	
	public Location(String name, String geomWkt) {
		this.name = name;
		this.geomWkt = geomWkt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Geometry getGeom() {
		return geom;
	}

	public void setGeom(Geometry geom) {
		this.geom = geom;
	}

	public String getGeomWkt() {
		return geomWkt;
	}

	public void setGeomWkt(String geomWkt) {
		this.geomWkt = geomWkt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geom == null) ? 0 : geom.hashCode());
		result = prime * result + ((geomWkt == null) ? 0 : geomWkt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (geom == null) {
			if (other.geom != null)
				return false;
		} else if (!geom.equals(other.geom))
			return false;
		if (geomWkt == null) {
			if (other.geomWkt != null)
				return false;
		} else if (!geomWkt.equals(other.geomWkt))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", geom=" + geom + ", geomWkt=" + geomWkt + "]";
	}

}
