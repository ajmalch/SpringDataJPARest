
pipeline {

    
    agent {
                  docker {
                      image 'maven:3-alpine'
                      args '-v /uesrs/AjmalCholassery/.m2:/root/.m2'
                  }
          }

    stages {
        stage('Build') {
            steps {

                    commitid = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
                    sh "mvn clean package -Dcommitid=${commitid}"


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
