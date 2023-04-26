pipeline {
    agent any

    stages {
        stage('clean') {
            steps {
               sh 'mvn clean'
            }
        }
        stage('compile') {
            steps {
                sh './mvnw compile'
            }
        }
        stage('test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('package') {
            steps {
                sh 'mvn package'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
                }
            }
        }
        stage('run') {
            steps {
                withEnv(['JENKINS_NODE_COOKIE=dontKillMe']){
                    sh 'nohup java -jar target/user-0.0.1-SNAPSHOT.jar &'
                }
            }
        }
    }
}
