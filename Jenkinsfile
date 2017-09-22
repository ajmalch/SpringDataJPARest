
pipeline {

    
    agent any

    stages {
        stage('Build') {
            steps {
               script {
                    commitid = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
                    sh "mvn clean package -Dcommitid=${commitid}"
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }

}
