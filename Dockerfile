FROM maven:3.8.5-openjdk-18 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:18
COPY --from=build /home/app/target/csv-parser-api.jar /usr/local/lib/csv-parser-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/csv-parser-api.jar"]