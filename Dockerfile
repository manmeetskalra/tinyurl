FROM maven:3.6.3-jdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests=true

FROM openjdk:13-jdk-alpine
COPY --from=build /usr/src/app/target/tiny-url-0.0.1-SNAPSHOT.jar /usr/src/app/tiny-url-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-mjar","/usr/src/app/tiny-url-0.0.1-SNAPSHOT.jar"]