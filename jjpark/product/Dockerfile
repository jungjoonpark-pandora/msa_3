FROM openjdk:11-jdk-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

#ENTRYPOINT ["./wait-for-it.sh", "gatewayserver:8080", "reivew_db:3307", "--strict", "--",  "java", "-jar", "app.jar"]
#ENTRYPOINT ["./wait-for-it.sh", "reivew_db:3306", "--strict", "--",  "java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]