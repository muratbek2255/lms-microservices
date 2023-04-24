FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN echo ${JAR_FILE} user.jar
ENTRYPOINT ["java","-jar","/user.jar"]
