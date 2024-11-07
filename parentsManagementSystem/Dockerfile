#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

RUN apt update && apt install -y iputils-ping

#Information around who maintains the image
MAINTAINER sojib.com
#Add the application's jar to the image
COPY target/parentsManagementSystem-0.0.1-SNAPSHOT.jar parentsManagementSystem-0.0.1-SNAPSHOT.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "parentsManagementSystem-0.0.1-SNAPSHOT.jar"]










