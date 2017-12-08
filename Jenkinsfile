
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

                    echo 'Biilding .'
                    //sh "mvn -B -DskipTests clean package"


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
