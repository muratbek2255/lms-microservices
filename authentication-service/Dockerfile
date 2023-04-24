FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
RUN echo ${JAR_FILE} app.jar
COPY . /app
ENTRYPOINT ["java","-jar","/app.jar"]
