FROM openjdk:17
COPY "./target/progkorny-0.0.2-SNAPSHOT.jar" "/app/progkorny-0.0.2-SNAPSHOT.jar"
EXPOSE 8080
CMD [ "java", "-jar", "/app/progkorny-0.0.2-SNAPSHOT.jar" ]