pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                sh docker-compose up
            }
        }
        stage('Deploy') {
            steps {
                sh docker-compose down
            }
        }
    }
}