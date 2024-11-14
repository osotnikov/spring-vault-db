FROM bellsoft/liberica-runtime-container:jre-21-glibc

ADD ./target/spring-vault-db.jar /app/app.jar

CMD ["java", "-XX:MaxRAMPercentage=40.0", "-jar", "/app/app.jar"]

EXPOSE 8090
