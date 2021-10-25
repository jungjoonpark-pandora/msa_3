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
              sh './gradlew bootJar'
          }
          dir('jjpark/gatewayserver') {
              sh './gradlew bootJar'
          }
          dir('jjpark/product') {
              sh './gradlew bootJar'
          }
          dir('jjpark/review') {
              sh './gradlew bootJar'
          }
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
            docker.build("jjpark/eurekaserver:latest", "-f eurekaserver/Dockerfile .")
            docker.build("jjpark/gatewayserver:latest", "-f gatewayserver/Dockerfile .")
//             def discovery = docker.build("wjsgus95/discovery:latest", "-f discovery/Dockerfile .")
//             def product = docker.build("wjsgus95/product:latest", "-f product/Dockerfile .")
//             def review = docker.build("wjsgus95/review:latest", "-f review/Dockerfile .")
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
