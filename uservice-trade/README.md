# Quality

- Checkstyle
- TestContainers
- Archunit
- Database upgrades with rollbacks

## Working with single services
- run local stack
- test local database migrations
- run service from vscode

## Known issues
- sudo chown -R $USER . (when Failed to clean project)

## Modularity decisions
- host application does not apply any database changes / migrations when started
- initdb
  - apply all database changes when started so that host may work properly
  - should not have any local dependencies do build as it is started from sources by docker-compose so dependencies are not build automatically
- migrated contsins database migrations so that it may be fired by initdb, and reused in tests by host
- tests module contains shared testing classes (we found a few as useful to share)
- we have two time-consumin migrations: in host tests, and initdb tests. We can't combine them, as initdb tests is focused on rollback operation what, in case of merging tests, would impact all host functional tests

## Apply liquibase changes to local stack
- Start **docker-compose up** to run just dockerized database server with all initdb scripts
- If you would like to remove images and start from scratch, stop **docker-compose down** and start again
- Play individual commands like:
  - **mvn -pl initdb liquibase:help**
  - **mvn -pl initdb liquibase:rollback -Dliquibase.rollbackTag=v0** do rollback to empty tables
  - **mvn -pl initdb liquibase:update** to aply all changes
  - **mvn -pl initdb liquibase:rollback -Dliquibase.rollbackCount=1** do rollback one change
- Or, test script manually **docker-compose up --build** to  run database and migrations

## Architectural decisions (simplified version):
- Use spring to run single uservices
- Use grpc to communicate between 
- use Gherkin to apply acceptance tests for the whole solution
- expose Webapi as reusable OpenFeign interfaces to avoid duplication of client/server contracts
