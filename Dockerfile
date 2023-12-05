FROM openjdk:17
LABEL maintainer="{akinyemisamuelayo@gmail.com} samuel"
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080