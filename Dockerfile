FROM openjdk:21

VOLUME /tmp

COPY target/*.jar userservices-0.0.1-SNAPSHOT.jar

EXPOSE 1234
ENTRYPOINT ["java","-jar","/userservices-0.0.1-SNAPSHOT.jar"]