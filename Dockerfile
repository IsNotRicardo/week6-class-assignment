FROM maven:latest

WORKDIR /app

COPY pom.xml /app/

COPY . /app/

RUN mvn clean package

CMD ["java", "-jar", "target/aikido.jar"]