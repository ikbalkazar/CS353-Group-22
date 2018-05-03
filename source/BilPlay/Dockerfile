FROM maven:3.5.3-jdk-10

RUN mkdir --parents /usr/src/bilplay
WORKDIR /usr/src/bilplay

ADD pom.xml /usr/src/bilplay
RUN mvn verify clean --fail-never

COPY . /usr/src/bilplay

RUN mvn package

CMD ["/usr/src/bilplay/run.sh"]