FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN echo ${JAR_FILE} library.jar
ENTRYPOINT ["java","-jar","/library.jar"]