pipeline {
  parameters {
    string(name: 'IMAGE_NAME', defaultValue: 'msa')
    string(name: 'CONTAINER_NAME', defaultValue: 'msa')
  }

  environment {
      WORK_DIR = 'jjpark'
  }

  agent any
  tools {
    jdk 'jdk-11'
  }
  stages {

   stage('Build Jar') {
      steps {
          dir('jjpark/eurekaserver') {
              sh './gradlew bootJar' //sh './gradlew clean build'
          }
          dir('jjpark/gatewayserver') {
              sh './gradlew bootJar' //sh './gradlew clean build'
          }
          dir('jjpark/product') {
              sh './gradlew bootJar' //sh './gradlew clean build'
          }
          dir('jjpark/review') {
              sh './gradlew bootJar' // sh './gradlew clean build'
          }
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
            eurekaserver = docker.build("jjpark/eurekaserver")
            gatewayserver = docker.build("jjpark/gatewayserver")
            product = docker.build("jjpark/product")
            review = docker.build("jjpark/review")
        }
      }
    }

    stage('compose up') {
      steps {
        dir("jjpark") {
          sh "docker-compose up -d"
        }
      }
    }
  }
}
