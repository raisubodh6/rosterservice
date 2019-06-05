FROM java:8
MAINTAINER raisubodh6@gmail.com

# Expose the container's network port: 9210
EXPOSE 9090
ADD /target/roster.jar roster.jar
ENTRYPOINT ["java", "-jar", "roster.jar"]