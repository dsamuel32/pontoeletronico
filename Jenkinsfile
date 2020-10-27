#!/usr/bin/env groovy

pipeline {
    agent any

    stages {
        
        stage('Preparando Ambiente') {
            steps {
                sh 'chmod 775 ./gradlew'
            }
        }
        
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }
        
        stage('Deploy Produção') {
            steps {
                script {
                    timeout(time: 10, unit: 'MINUTES') {
                        input(id: "Deploy Gate", message: "Deploy em produção?", ok: 'Deploy')
                    }
               }
            }
        }

    }
}
