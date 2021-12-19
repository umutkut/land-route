FROM openjdk:11
ARG JAR_FILE=target/*.jar
ARG JSON_FILE=target/countries.json
COPY ${JAR_FILE} app.jar
COPY ${JSON_FILE} ./
ENTRYPOINT ["java","-jar","/app.jar"]