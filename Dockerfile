FROM  maven:openjdk
ENV PATH $PATH:/usr/share/maven
COPY pom.xml usr/share/maven/
COPY .   /usr/share/maven/
WORKDIR usr/share/maven
