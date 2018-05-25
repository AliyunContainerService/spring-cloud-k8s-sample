FROM openjdk:8-alpine
#VOLUME /tmp
ADD app.jar app.jar
RUN sh -c 'touch /app.jar'


# use k8s readiness check instead of docker health check
 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

