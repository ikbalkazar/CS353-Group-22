FROM maven:3.5.3-jdk-10

RUN mkdir --parents /usr/src/bilplay
WORKDIR /usr/src/bilplay

ADD pom.xml /usr/src/bilplay
RUN mvn verify clean --fail-never

COPY . /usr/src/bilplay

RUN mvn package

CMD ["java", "-jar", "/usr/src/bilplay/target/BilPlay-1.0.jar", "server", "config.yml"]