FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar newsData.jar
ENTRYPOINT ["java","-jar","/newsData.jar"]