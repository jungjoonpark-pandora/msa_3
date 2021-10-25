pipeline {

  agent any

  tools {
    jdk 'jdk-11'
  }

  stages {

   stage('Build Jar') {
      steps {
          dir('jjpark/eurekaserver') {
              //sh './gradlew bootJar'
              sh './gradlew clean build'
          }
          dir('jjpark/gatewayserver') {
              //sh './gradlew bootJar'
              sh './gradlew clean build'
          }
          dir('jjpark/product') {
              //sh './gradlew bootJar'
              sh './gradlew clean build'
          }
          dir('jjpark/review') {
              //sh './gradlew bootJar'
              sh './gradlew clean build'
          }
      }
    }

//     stage('Initialize') {
//         def dockerHome = tool 'myDocker'
//         env.PATH = "${dockerHome}/bin:${env.PATH}"
//     }

    stage('Build Docker Image') {
      steps {
        script {
            def eurekaserver = docker.build("eurekaserver:latest", "-f eurekaserver/Dockerfile .")
            def gatewayserver = docker.build("gateway:latest", "-f gatewayserver/Dockerfile .")
            def product_service = docker.build("product_service:latest", "-f product/Dockerfile .")
            def review_service = docker.build("review_service:latest", "-f review/Dockerfile .")
//             eurekaserver = docker.build("jjpark/eurekaserver")
//             gatewayserver = docker.build("jjpark/gatewayserver")
//             product = docker.build("jjpark/product")
//             review = docker.build("jjpark/product")
        }
      }
    }

    stage('Docker Compose Up') {
      steps {
        dir(env.WORK_DIR) {
          sh "docker-compose up -d"
        }
      }
    }
  }
}
