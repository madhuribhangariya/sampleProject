pipeline {
    agent any

    stages {
        stage('Start docker-compose') {	
	          sh 'docker-compose up -d --scale chrome=5 --scale firefox=0'
}

    }
}