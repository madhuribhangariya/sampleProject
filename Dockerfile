FROM  maven:openjdk
COPY pom.xml usr/share/maven/
COPY .   /usr/share/maven/
WORKDIR usr/share/maven
RUN mvn -f pom.xml -DsuiteXmlFile=${params.suiteXmlFile}
