FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN echo ${JAR_FILE} chat.jar
ENTRYPOINT ["java","-jar","/chat.jar"]
