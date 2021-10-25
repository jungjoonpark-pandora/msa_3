pipeline {

    environment {
        registry = "jjpark/centos_telnet"
        registryCredential = 'test'
        def BUILDVERSION = sh(script: "echo `date +%s`", returnStdout: true).trim()
    }

    agent any

    tools {
        jdk 'jdk-11'
    }

    stages {

//         stage('Initialize') {
//             def dockerHome = tool 'myDocker'
//             env.PATH = "${dockerHome}/bin:${env.PATH}"
//         }

        stage('Build Jar') {
            steps {
                dir('jjpark/eurekaserver') {
                    sh './gradlew clean build'
                }
                dir('jjpark/gatewayserver') {
                    sh './gradlew clean build'
                }
                dir('jjpark/product') {
                    sh './gradlew clean build'
                }
                dir('jjpark/review') {
                    sh './gradlew clean build'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir(env.WORK_DIR) {
                    script {
                        sh 'docker build -t eureka eurekaserver'
                        sh 'docker build -t gateway gatewayserver'
                        sh 'docker build -t product_service product'
                        sh 'docker build -t review_service review'
                    }
                }
            }
        }

        stage('Deploy Docker Images') {

        }

        stage('Docker Compose Up') {
            steps {
                dir(env.WORK_DIR) {
                    sh "docker-compose up -d"
                }
            }
        }

        stage('Clean') {
            steps {
                dir(env.WORK_DIR) {
                    sh "docker rmi eureka"
                    sh "docker rmi gateway"
                    sh "docker rmi product_service"
                    sh "docker rmi review_service"
                }
            }
        }
    }
}
