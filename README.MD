# Helip Backend

## Description

Helip Backend is a Spring Boot RESTful API that interacts with a PostgreSQL + PostGIS database deployed on Heroku. 

The application processes a large JSON file, storing its data in the database. 

It provides an endpoint (`api/nearby`) that performs PostGIS geometry queries to return locations near a given latitude and longitude.

## Installation

```bash
git clone https://github.com/kridesav/Helip_backend.git
```

This project uses Maven for dependency management and build automation. The `pom.xml` file at the root of the project contains the project setup details.

### Java Version

The project uses Java 17.

### Dependencies

The project uses several dependencies, including:

- Spring Boot Starter Batch for batch processing
- Spring Boot Starter Data JPA for data persistence
- Spring Boot Starter Security for security features
- Spring Boot Starter Web for creating web applications
- Spring Boot Devtools for development-time features
- Firebase Admin SDK for interacting with Firebase services
- JTS (Java Topology Suite) for creating and manipulating vector geometry
- PostgreSQL JDBC Driver for connecting to PostgreSQL databases
- Hibernate Spatial for handling geographic data
- Hibernate Core for ORM functionality

### Build Plugins

The project uses the Spring Boot Maven Plugin for building Spring Boot applications and the Maven Compiler Plugin to ensure the project is compiled using Java 17.

## Usage

Project is currently configured to be deployed to Heroku.

Main branch is H2 version that will struggle with memory limits of Herokus basic plans, postgre branch is most up-to-date version that is currently being used.

To get more info about deploying to heroku with spring boot: [Heroku documentation](https://devcenter.heroku.com/categories/working-with-spring-boot)

You will have to also create a database and add postgis plugin either in Heroku web app or with Heroku CLI.

Guide for Heroku CLI: [Heroku documentation](https://devcenter.heroku.com/articles/heroku-postgres-extensions-postgis-full-text-search#postgis)

You will also need to insert your Firebase serviceaccount key to Heroku Config Vars.

It is also recommended to insert

```bash
JAVA_TOOL_OPTIONS = -XX:+UseContainerSupport
```

when using Spring boot with Heroku for memory calculation reasons.

## API Endpoints

- `GET /api/places`: Returns a list of all features. Requires an `Authorization` header with a valid Firebase ID token.
- `GET /api/places/nearby?lat={latitude}&lon={longitude}`: Returns a list of locations near the given latitude and longitude. Requires an `Authorization` header with a valid Firebase ID token.
- `GET /api/places/search?param={name}`: Returns a list of features that match the given name. Requires an `Authorization` header with a valid Firebase ID token.

## Database Schema

The application uses a PostgreSQL database with PostGIS extension. 

Database is persistent but currently cleaned every reboot.

The main entity in the database is the `Feature`, which has the following structure:

### Feature

- `type`: String
- `properties`: Properties
- `geometry`: Geometry

### Properties

- `id`: Long
- `katuosoite`: String
- `kunta_nimi`: String
- `kuntanumer`: Integer
- `lisatieto_`: String
- `muokattu_v`: String
- `nimi_fi`: String
- `nimi_se`: String
- `omistaja`: String
- `piste_json`: String
- `postinumer`: String
- `postitoimi`: String
- `puhelinnum`: String
- `sahkoposti`: String
- `sijainti_i`: Integer
- `tyyppi_n_1`: String
- `tyyppi_n_2`: String
- `tyyppi_nim`: String
- `tyyppikood`: Integer
- `www`: String
- `x`: Double
- `y`: Double
- `yllapitaja`: String

### Geometry

- `type`: String
- `coordinates`: Array of Double

The `Feature` object is constructed from a JSON file and stored in the database. The `properties` and `geometry` fields are nested objects within the `Feature` object. The `coordinates` field in the `Geometry` object is an array of doubles.

## Data Preprocessing

This project includes two Python scripts for cleaning the input JSON file:

1. `clean_json.py`: The cleanTyyppi.py script is used to clean the JSON data file. It loads the data from JSON file, removes certain features based on the tyyppi_nim property, and then writes the cleaned data back to the same file. The features to be removed are defined in the remove_values list.

2. `checkTyyppi.py`: This script counts the occurrences of each unique "tyyppi_nim" value in the JSON file. It loads the JSON file, iterates over the features, and increments a count for each "tyyppi_nim" value. The counts are then printed in descending order.

To run these scripts, use the following commands:

```bash
python clean_json.py
python checkTyyppi.py
```