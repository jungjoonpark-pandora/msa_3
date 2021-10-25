pipeline {
  parameters {
    string(name: 'IMAGE_NAME', defaultValue: 'hello')
    string(name: 'CONTAINER_NAME', defaultValue: 'hello')
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
         dir(env.WORK_DIR) {
            sh "chmod +x ./gradlew"
            sh "./gradlew clean build"
         }
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
          docker.build(params.IMAGE_NAME)
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
