FROM adoptopenjdk/openjdk11

WORKDIR /app

COPY build/libs/4rent-0.0.1-SNAPSHOT.jar /app/4rent.jar

ENTRYPOINT ["java","-jar","4rent.jar"]