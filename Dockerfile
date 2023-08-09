FROM maven:latest AS stage1
WORKDIR /StroyMaster
COPY pom.xml /StroyMaster
RUN mvn dependency:resolve
COPY . /StroyMaster
RUN mkdir /StroyMaster/jarfiles
RUN cp /StroyMaster/target/StroyMaster-1.0.jar /StroyMaster/jarfiles
RUN mvn clean

FROM openjdk:17 AS final
COPY --from=stage1 /StroyMaster/jarfiles/StroyMaster-1.0.jar StroyMaster-1.0.jar
EXPOSE 8080
CMD ["java", "-jar", "StroyMaster-1.0.jar"]
