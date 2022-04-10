 pipeline {

    agent none

    stages {
      stage(‘Build’) {
        steps {
          sh 'usr/share/maven/sample/docker-compose up --build'
        }
      }
    }
}