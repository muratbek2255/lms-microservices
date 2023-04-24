FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN echo ${JAR_FILE} course.jar
ENTRYPOINT ["java","-jar","/course.jar"]