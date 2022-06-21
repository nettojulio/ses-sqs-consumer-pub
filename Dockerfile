FROM maven:3.8.6-openjdk-18-slim
ARG JAR_FILE=target/*.jar
COPY . /tmp_build
WORKDIR /tmp_build
RUN mvn -T 1C clean install -Dmaven.test.skip -DskipTests -Dmaven.javadoc.skip=true \
    && cp ${JAR_FILE} /app.jar \
    && rm -fr * \
    && rm -rf /tmp_build
EXPOSE 8080
COPY ./newrelic-api.jar /newrelic-api.jar
COPY ./newrelic.jar /newrelic.jar
COPY ./newrelic.yml /newrelic.yml

ENTRYPOINT ["java","-javaagent:/newrelic.jar","-jar","/app.jar"]