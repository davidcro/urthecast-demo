--if postgis not installed on schema then run the following
CREATE EXTENSION postgis;
CREATE EXTENSION postgis_topology;

--table construction
DROP TABLE IF EXISTS location;
CREATE TABLE location (id serial PRIMARY KEY, name VARCHAR(75), geom GEOMETRY);

--data
INSERT INTO location (name, geom) VALUES ('GALWAY', ST_GeomFROMText('POINT(-9.062691 53.270962)'));
INSERT INTO location (name, geom) VALUES ('LONDON', ST_GeomFROMText('POINT(-0.118092 51.509865)'));
INSERT INTO location (name, geom) VALUES ('BIRMINGHAM', ST_GeomFROMText('POINT(-1.898575 52.489471)'));
INSERT INTO location (name, geom) VALUES ('CORK', ST_GeomFROMText('POINT(-8.468399 51.903614)'));
INSERT INTO location (name, geom) VALUES ('CARDIFF', ST_GeomFROMText('POINT(-3.179090 51.481583)'));
INSERT INTO location (name, geom) VALUES ('BELFAST', ST_GeomFROMText('POINT(-5.926437 54.607868)'));
INSERT INTO location (name, geom) VALUES ('GLASGOW', ST_GeomFROMText('POINT(-4.251806 55.864237)'));
INSERT INTO location (name, geom) VALUES ('EDINBURGH', ST_GeomFROMText('POINT(-3.188267 55.953251)'));
INSERT INTO location (name, geom) VALUES ('LIMERICK', ST_GeomFROMText('POINT(-8.630498 52.668018)'));
INSERT INTO location (name, geom) VALUES ('WATERFORD', ST_GeomFROMText('POINT(-7.499998 52.249999)'));
INSERT INTO location (name, geom) VALUES ('PARIS', ST_GeomFROMText('POINT(2.349014 48.864716)'));
INSERT INTO location (name, geom) VALUES ('VANCOUVER', ST_GeomFROMText('POINT(-123.116226 49.246292)'));

