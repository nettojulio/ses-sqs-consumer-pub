FROM nettojulio/ci-cd-e8:java-maven
ARG JAR_FILE=target/*.jar
COPY . /tmp_build
WORKDIR /tmp_build
RUN mvn -T 1C clean install -Dmaven.test.skip -DskipTests -Dmaven.javadoc.skip=true \
    && cp ${JAR_FILE} /app.jar \
    && rm -fr *
EXPOSE 8080
COPY ./newrelic /newrelic
COPY ./newrelic-api.jar /newrelic-api.jar
COPY ./newrelic.jar /newrelic.jar
COPY ./newrelic.yml /newrelic.yml

ENTRYPOINT ["java","-javaagent:/newrelic.jar","-jar","/app.jar"]