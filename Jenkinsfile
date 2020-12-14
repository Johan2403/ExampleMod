pipeline {
  agent any
  stages {
    stage('Grant Permission') {
      steps {
        sh 'chmod +x gradlew'
      }
    }  
    stage('Build') { // This takes a lot of time...
      steps {
        echo 'Building ExampleMod'
        sh './gradlew build -Dorg.gradle.jvmargs=-Xmx756M'
      }
    }

    stage('Test') {
      steps {
        echo 'Testing ExampleMod'
        sh './gradlew test -Dorg.gradle.jvmargs=-Xmx756M'
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
