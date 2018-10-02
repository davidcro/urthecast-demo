package com.dcrowe.entity;
/**
 * Enum of countries to WKT bounding boxes
 * @author davidcrowe
 *
 */
public enum BoundingBox {
	
	IRELAND("POLYGON((-11.59 55.44, -5.38 55.44, -5.38 51.28, -11.59 51.28, -11.59 55.44))"),
	UK("POLYGON((-5.83 60.73, 1.52 60.73, 1.52 49.56, -5.83 49.56, -5.83 60.73))"),
	FRANCE("POLYGON((-4.36 50.17, 8.32 50.17, 8.32 42.33, -4.36 42.33, -4.36 50.17))"),
	CANADA("POLYGON((-142.6 83.0, -53.2 83.0, -53.2 44.6, -142.6 44.6, -142.6 83.0))");
	
	private String boundingBoxWkt;

	public String getBoundingBoxWkt() {
		return boundingBoxWkt;
	}
	
	BoundingBox(String boundingBoxWkt) {
		this.boundingBoxWkt = boundingBoxWkt;
	}

}