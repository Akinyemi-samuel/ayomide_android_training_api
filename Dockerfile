FROM openjdk:17
LABEL maintainer="{akinyemisamuelayo@gmail.com} samuel"
WORKDIR /app
COPY target/ayomide-api-training.jar /app
ENTRYPOINT ["java", "-jar", "/app/ayomide-api-training.jar"]
EXPOSE 8080