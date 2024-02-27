# Charging Station Validation Service Documentation

## Overview
This document outlines the implementation and usage of a web service designed for validating charging station data for electric vehicles. The service is capable of handling data from both browser-based and mobile UI clients, supporting multilingual functionality to cater to a diverse user base. The primary goal is to assist clients in identifying and presenting any issues or errors in the charging station data to the end-users effectively.

## Installation and Setup
### Prerequisites
- Java Development Kit (JDK) - Version 11 or above
- Maven - For dependency management and project build
- Any IDE that supports Java (e.g., IntelliJ IDEA, Eclipse)

### Steps
1. #### Clone the Repository
Begin by cloning the public Git repository to your local machine using the following command:
```bash
git clone <https://github.com/DimkaGrek/ChargingStation>
```
2. #### Build the Project
Navigate to the project directory and build the project using Maven:
```bash
cd <project-directory>
mvn clean install
```
3. #### Run the Application
After a successful build, run the application using:
```bash
java -jar target/<jar-file-name>.jar
```
After starting, the service will be available at http://localhost:8080.

## Usage
### Endpoint
The service exposes an HTTP POST endpoint that accepts JSON formatted data representing the charging station and its details.

- #### Endpoint: `/api/validate`
- #### Method: `POST`
- #### Content-Type: `application/json`

## Request Format
The request body should follow the JSON structure outlined below:

```json
{
  "id": "<unique-identifier>",
  "title": "<station-title>",
  "description": "<station-description>",
  "address": "<postal-address>",
  "geoCoordinates": "<latitude, longitude>",
  "isPublic": "<true/false>",
  "connectors": [
    {
      "id": "<connector-identifier>",
      "type": "<CCS/CHAdeMO/Type1/Type2>",
      "maxPower": "<maximum-power-kW>"
    }
  ]
}
```
## Validation Rules
- ### Charging Station
  - id, isPublic, and connectors are mandatory.
    - title, description, address, and geoCoordinates are required for public stations. 
    - The connectors list must contain 1 to 8 connectors. 
- ### Connector
  - All fields (id, type, maxPower) are mandatory.

## Response
The service will return a JSON response indicating the validation result. For valid data, it will confirm the validation success, and for invalid data, it will detail the errors encountered.

## Examples

- ### Valid Request (status code 200)
```json
{
"message": "Data validated successfully."
}
```
- ### Bad Request (status code 400)
```json
{
  "address": "size must be between 10 and 100",
  "description": "size must be between 10 and 255",
  "geoCoordinates": "must match 'latitude, longitude'",
  "title": "size must be between 1 and 100"
}
```
- ### Additional Error Handling
- `404 Not Found` - The requested resource was not found.
- `500 Internal Server Error` - Internal server error.

## Multilingual Support
The service supports multilingual messages for validation errors. It auto-detects the client's language preference based on the `Accept-Language` header in the request. Ensure your client specifies this header to receive error messages in the desired language.

## Conclusion
This service provides a robust solution for validating charging station data, ensuring compliance with defined rules and standards. By following the installation and usage guidelines, clients can seamlessly integrate this validation mechanism into their systems, enhancing data integrity and user experience.
