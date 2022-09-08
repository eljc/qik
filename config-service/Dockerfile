FROM adoptopenjdk/openjdk11:alpine-jre
# cd /opt/app
WORKDIR /opt/app

# cp target/jar-file /opt/app/app.jar
COPY target/config-service-0.0.1-SNAPSHOT.jar app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]