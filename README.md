---

## Microservices Architecture

The Smart Grid Energy Telemetry and Anomaly Monitoring
System follows a microservices architecture to provide
independent and scalable services.

### 1. Authentication Service
- Handles user registration and login.
- Provides authentication using JWT.
- Secures application endpoints.

### 2. Meter Service
- Registers smart meters.
- Stores meter details.
- Manages meter information.

### 3. Usage Service
- Collects electricity consumption readings.
- Stores energy usage information.
- Generates usage reports.

### 4. Alert Service
- Monitors electricity consumption.
- Checks readings against configured thresholds.
- Generates alerts when usage exceeds the limit.

### 5. API Gateway
- Acts as the entry point for client requests.
- Routes requests to the appropriate microservices.

### 6. Eureka Server
- Provides service discovery.
- Maintains the registry of available microservices.

## Sample Energy Monitoring Scenario

Consider a smart meter with a configured threshold
of 100 kWh.

| Meter Reading | Threshold | Result |
|---|---|---|
| 80 kWh | 100 kWh | Normal Usage |
| 120 kWh | 100 kWh | Alert Generated |

The system helps consumers and facility managers
monitor electricity usage and identify unusually
high consumption.

## Project Benefits

- Independent microservice deployment.
- Improved scalability.
- Centralized API routing.
- Automatic service discovery.
- Early identification of unusual electricity usage.