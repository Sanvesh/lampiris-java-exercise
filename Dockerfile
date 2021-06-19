FROM openjdk:11
EXPOSE 8080
ADD target/*.jar dockerapp.jar
ENTRYPOINT ["java", "-jar", "/dockerapp.jar" ]