FROM maven:3.8.1-jdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11
WORKDIR /app
COPY --from=build /home/app/target/cliente.jar /app/cliente.jar

EXPOSE 80

CMD java -jar cliente.jar