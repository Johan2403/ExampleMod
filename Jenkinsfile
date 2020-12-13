pipeline {
  agent any
  stages {
    stage('Grant Permission') { // Grants permission
      steps {
        sh 'chmod +x gradlew'
      }
    }  
    stage('Build') {
      steps {
        echo 'Building ExampleMod'
        sh './gradlew build'
      }
    }

    stage('Test') {
      steps {
        echo 'Testing ExampleMod'
        sh './gradlew test'
      }
    }

  }
  post {
    always {
      script {
        archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true, onlyIfSuccessful: true, allowEmptyArchive: true
      }

    }

  }
}
