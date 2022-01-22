FROM  maven:openjdk
COPY pom.xml usr/share/maven/
COPY .   /usr/share/maven/
WORKDIR usr/share/maven