#FROM openjdk:8
FROM openjdk:17-slim
FROM tomcat:9-jdk17-openjdk-slim
VOLUME /app/log
ADD target/empgps_tracking.jar empgps_tracking.jar
EXPOSE 2024
ENTRYPOINT ["java", "-jar", "empgps_tracking.jar"]



# Use the official Tomcat base image
#FROM tomcat:9-jdk17-openjdk-slim

# Remove the default Tomcat applications
#RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file into the webapps directory of Tomcat
#COPY target/empgps_tracking.war /usr/local/tomcat/webapps/ROOT.war

# Expose the port that Tomcat will run on (default is 8080)
#EXPOSE 8080

# Specify the command to run on container startup
#CMD ["catalina.sh", "run"]











