UrtheCast demo

Stack:
-Java
-Spring Boot
-Hibernate
-Postgres/Postgis

Steps to run:
-run src/main/resources/postgres_init.sql to set up database
-configure src/main/resources/application.properties for your own local settings
-open a command prompt/terminal, navigate to project folder and run "mvn spring-boot:run"
	-assuming that you have maven installed
-note: you will see the following non-fatal error on app startup
	-Caused by: java.sql.SQLFeatureNotSupportedException: Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented.
	-this is a known JPA/Atomikos issue

Two API calls implemented:
/locations/{country} - GET
-this will retrieve all locations in a given country based on the country's bounding box
-four countries supported: Ireland, Canada, UK, France
-e.g. localhost:8080/locations/canada

/locations - POST (provide json)
-this will add a new location to the database
-name of location and WKT coords to be provided
-e.g. {"name":"EDMONTON","geomWkt":"POINT (-113.323975 53.631611)"}

TODO:
-basic authentication for APIs
-more unit tests (I only quickly added a couple)
-more/better error handling (error pages etc.)

