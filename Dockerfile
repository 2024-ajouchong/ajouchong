FROM gradle:8.8-jdk21 AS builder
WORKDIR /ajouchong
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle bootJar --no-daemon


FROM openjdk:21-jdk-slim
WORKDIR /ajouchong
COPY --from=builder /ajouchong/build/libs/*.jar /ajouchong/ajouchong-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "ajouchong-0.0.1-SNAPSHOT.jar"]