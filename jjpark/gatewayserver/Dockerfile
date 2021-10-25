FROM openjdk:11-jdk-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh
#ENTRYPOINT ["./wait-for-it.sh", "localhost:8761", "--strict", "--","java","-jar","/app.jar"]
ENTRYPOINT ["java","-jar","/app.jar"]
