# modern-trade-platform
Microservice-based trading application featuring the following:
- Containerised Postgres database with liquibase versioning
- GRPC communication between services with DAPR as the runtime
- REST API with swagger UI for backend service communication
- Gherkin testing for each service (not existing yet)
- GraphQL webAPI (not existing yet)

## Naming convention for ...
### Root folders
- infra - Terraform definition of the environments
- helm - HELM definition to install the all µservices in k8s namespace
- api - location for the gRPC api proto files for each service, and client building project for java (+ other languages in future if needed)
- libs-java - reusable code to reuse in uservices, deployed to external maven repository
- service-* - folder with a local stack service, designed to be run by root docker-compose
- uservice-* - seperated µservice designed to be compiled, developed and run separately. It may contains initdb subproject to apply database migration
### Other folders
- .api.events - (not existing yet) proposed name for place of avro api, exposed in known-location for other services/.
### Other files
- Dockerfile.local - image definition used just fo local development process, not to build final images

## How to run acceptance tests
### By developers:
- run local stack of services **docker-compose up** or, to recompile and run **docker-compose up --build**
- TBD: open and run test project
- TBD: Review result of gherkin reports

## Avoid clash of ports:
Rule: Use scope 18*** to simplify our management of ports, use different ports to run uservices locally
### Webapi Service
- dapr app id: uservice-webapi
- HTTP: 18010
- GRPC: 18011
- Swagger URL: http://localhost:8080/swagger-ui/index.html

### Trade Service
- dapr app id: uservice-deal
- HTTP: 18120
- GRPC: 18121

### Calculation Service
- dapr app id: uservice-calculation
- HTTP: 18220
- GRPC: 18221

### Lifecycle Service
- dapr app id: uservice-lifecycle
- HTTP: 18320
- GRPC: 18321

### Market Data Service
- dapr app id: uservice-market-data
- HTTP: 18420
- GRPC: 18421

### TODO
- Deployment to Kubernetes
- Sharing event models using Avro
- Mapping gRpc models without NPE exceptions (using proper mapper)
- GraphQL uservice
- To discuss: Monitoring - Integration with OTL / Application Insight

### Java platform
- Java 17
  - distribution: Eclipse, version: 17.0.4 temurin, used by developers and as base docker image. Personal preference, many other alternatives.
