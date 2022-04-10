 pipeline {

    agent any

    stages {
      stage(‘Build’) {
        steps {
		script{
               bat 'docker-compose up'
			   }
        }
      }
    }
}