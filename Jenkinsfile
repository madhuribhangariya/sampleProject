 pipeline {

    agent any

    stages {
      stage(‘doccker compose start’) {
        steps {
		script{
               bat 'docker-compose up --build'
			   }
        }
      }
	  stage(‘docker compose stop’) {
        steps {
		script{
               bat 'docker-compose down'
			   }
        }
      }
    }
}