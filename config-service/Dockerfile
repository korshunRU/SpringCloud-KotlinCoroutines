FROM maven:3.6.3 AS maven
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package

FROM openjdk:11-jdk-oracle
ARG JAR_FILE=config-service-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY --from=maven /usr/src/app/config-service/target/${JAR_FILE} /opt/app/
ENTRYPOINT ["java","-jar","config-service-0.0.1-SNAPSHOT.jar"]