# Camunda BPMN Documentation Generator

*Description TBD*

## IDE Formatting Configuration

Please read the [IDE Formatting Configuration](src/docs/config/ide_config.md) to configure your IDE properly for editing
kotlin files.

## The build of plugin description and upload to Maven-Repo

    // https://help.liferay.com/hc/en-us/articles/360018170131-Maven-Plugin-Builder-Gradle-Plugin

    ./gradlew buildPluginDescriptor
    ./gradlew build
    ./gradlew publishToMavenLocal

## The configuration of the plugin in the POM of target-project

    <plugins>
        ... 
        <plugin>
            <groupId>info.novatec</groupId>
            <artifactId>camunda-bpmn-documentation-generator</artifactId>
            <version>1.0-SNAPSHOT</version>
            <!-- optionaly --> 
            <executions>
                <execution>
                    <id>generate</id>
                    <phase>install</phase>
                    <goals>
                        <goal>generate</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
        ...
    </plugins>

## Run the goal of the plugin on the target-project 

    /opt/maven/bin/mvn cbdg:generate