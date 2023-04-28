FROM openjdk:18

ADD target/csv-parser-api.jar csv-parser-api.jar
ENTRYPOINT ["java", "-jar", "/csv-parser-api.jar"]