FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN echo ${JAR_FILE} discovery-jar.jar
COPY . /app
ENTRYPOINT ["java","-jar","/discovery-server.jar"]
