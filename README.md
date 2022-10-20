# Camunda BPMN Documentation Generator

[Test Project](https://github.com/NovatecConsulting/cbdg-maven-test)

## Code documentation
Hosted via [github pages](https://novatecconsulting.github.io/camunda-bpmn-documentation-generator/)

## IDE Formatting Configuration

Please read the [IDE Formatting Configuration](src/docs/config/ide_config.md) to configure your IDE properly for editing
kotlin files.

## The build and upload to Maven-Repo
    
Creating the Jar-Module with 

    ./gradlew clean build

Publish to local maven repository for usage in a maven project    

    ./gradlew publishToMavenLocal
