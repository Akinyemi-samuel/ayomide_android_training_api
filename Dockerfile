FROM eclipse-temurin:17-jdk-alpine
LABEL maintainer="{akinyemisamuelayo@gmail.com} samuel"
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080