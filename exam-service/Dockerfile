FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN echo ${JAR_FILE} exam.jar
ENTRYPOINT ["java","-jar","/exam.jar"]