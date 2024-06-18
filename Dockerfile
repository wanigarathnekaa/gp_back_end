FROM openjdk:17

EXPOSE 8080

ADD target/gp_back_end.jar gp_back_end.jar

ENTRYPOINT ["java", "-jar", "/gp_back_end.jar"]