pipeline {

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

    stage('Docker Compose Up') {
      steps {
        dir(env.WORK_DIR) {
          sh "docker-compose up -d"
        }
      }
    }
  }
}
