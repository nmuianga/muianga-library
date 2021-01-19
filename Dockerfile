FROM adoptopenjdk:11-jre-hotspot
RUN mkdir /opt/app
COPY /target/muianga-library-1.0.0-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/muianga-library-1.0.0-SNAPSHOT.jar"]