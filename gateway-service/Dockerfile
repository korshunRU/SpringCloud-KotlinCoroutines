FROM maven:3.6.3 AS maven
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package

FROM openjdk:11-jdk-oracle
ARG JAR_FILE=gateway-service-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY --from=maven /usr/src/app/gateway-service/target/${JAR_FILE} /opt/app/
ENTRYPOINT ["java","-jar","-Dhost=host.docker.internal","gateway-service-0.0.1-SNAPSHOT.jar"]