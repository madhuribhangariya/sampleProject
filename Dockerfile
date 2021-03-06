FROM  maven:openjdk
ENV PATH $PATH:/usr/share/maven
RUN mkdir /usr/share/maven/sample/
COPY pom.xml usr/share/maven/sample/
COPY .   /usr/share/maven/sample/
WORKDIR usr/share/maven/sample/
RUN  mvn -f pom.xml clean test -DsuiteXmlFile="src/test/resources/testData/apiTestng.xml"

