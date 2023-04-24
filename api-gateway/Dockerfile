FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN echo ${JAR_FILE} api-gateway.jar
COPY . /app
ENTRYPOINT ["java","-jar","/api-gateway.jar"]