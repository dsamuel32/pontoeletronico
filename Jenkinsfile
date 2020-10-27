#!/usr/bin/env groovy

pipeline {
    agent any

    stages {
        stage('Config Java') {
            steps {
                sh 'export JAVA_HOME=/var/jenkins_home/bibliotecas/jdk15/'
                sh 'PATH=$JAVA_HOME/bin:$PATH'
            }
        }
        
        stage('test') {
            steps {
                sh 'gradlew test'
            }
        }

        stage('build') {
            steps {
                sh 'gradlew build'
            }
        }

    }

}
