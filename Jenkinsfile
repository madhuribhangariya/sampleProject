 pipeline {

    agent any

    stages {
      stage(‘Build’) {
        steps {
		script{
               bat 'docker-compose up --build hub firefox chrome'
			   }
        }
      }
	  stage(‘Deploy’) {
        steps {
		script{
               bat 'docker-compose down'
			   }
        }
      }
    }
}
