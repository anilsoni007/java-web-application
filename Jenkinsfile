pipeline {
    agent any
    stages {
        stage('CheckoutCode'){
            steps {
                sh 'git clone https://github.com/anilsoni007/java-web-application.git'
            }
        }
        stage('Build'){
            steps{
                sh 'mvn clean package'
            }
        }
    }
}
