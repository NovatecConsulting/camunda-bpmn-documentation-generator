# Camunda BPMN Documentation Generator

*Description TBD*

## IDE Formatting Configuration

Please read the [IDE Formatting Configuration](src/docs/config/ide_config.md) to configure your IDE properly for editing
kotlin files.

## The build of plugin description and upload to Maven-Repo
    
We are creating the maven plugin descriptor with [Maven Plugin Builder Gradle Plugin](https://help.liferay.com/hc/en-us/articles/360018170131-Maven-Plugin-Builder-Gradle-Plugin "Maven Plugin Builder Gradle Plugin")
The follow gradle task generates the maven descriptor into directory *src/main/resources*. The gradle task *build* depends on this task

    ./gradlew buildPluginDescriptor

Publish to local maven repository for usage in a maven project    

    ./gradlew publishToMavenLocal

## The configuration of the plugin in the POM of target-project

    <plugins>
        ... 
        <plugin>
            <groupId>info.novatec</groupId>
            <artifactId>camunda-bpmn-documentation-generator</artifactId>
            <version>1.0-SNAPSHOT</version>
            <!-- optionaly, executes with install --> 
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

    ./mw cbdg:generate
