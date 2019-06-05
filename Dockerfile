FROM java:8
MAINTAINER raisubodh6@gmail.com

COPY ./target/roster-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app
EXPOSE 9090
RUN sh -c 'touch roster-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","roster-0.0.1-SNAPSHOT.jar"]