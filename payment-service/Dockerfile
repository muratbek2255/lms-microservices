FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN echo ${JAR_FILE} payment.jar
ENTRYPOINT ["java","-jar","/payment.jar"]