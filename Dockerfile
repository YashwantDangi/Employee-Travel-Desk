FROM openjdk:17-oracle
VOLUME /tmp
ARG JAR_FILE
ADD target/${JAR_FILE} /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]