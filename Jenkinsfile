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
            review = docker.build("jjpark/review:latest")
        }
      }
    }
    
    stage('Run Docker Container') {
      steps {
        sh """
          docker stop ${params.CONTAINER_NAME} && docker rm ${params.CONTAINER_NAME}
          docker run -d --name ${params.CONTAINER_NAME} -p 8080:8080 ${params.IMAGE_NAME}
        """
      }
    }
  }
}
