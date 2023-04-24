FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN echo ${JAR_FILE} consumer.jar
ENTRYPOINT ["java","-jar","/consumer.jar"]
