#
#Build stage
#
FROM maven:3.9.5-amazoncorretto-21 AS BUILD
COPY pom.xml              /tmp/
COPY brainfree_logic      /tmp/brainfree_logic/
COPY brainfree_model      /tmp/brainfree_model/
COPY brainfree_rest       /tmp/brainfree_rest/
WORKDIR                   /tmp/

RUN mvn clean install
#
# Package stage
#
FROM amazoncorretto:17
COPY --from=BUILD /tmp/brainfree_rest/target/brainfree.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod", "app.jar"]
