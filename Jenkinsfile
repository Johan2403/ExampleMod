pipeline {
  agent any
  environment {
        GRADLE_ARGS = '--console=plain -Dorg.gradle.jvmargs=-Xmx756M'
        DISCORD_WEBHOOK = credentials('discord-jenkins-webhook')
        DISCORD_PREFIX = "Job: ${JOB_NAME} Branch: ${BRANCH_NAME} Build: #${BUILD_NUMBER}"
        JENKINS_HEAD = 'https://wiki.jenkins-ci.org/download/attachments/2916393/headshot.png'
        COMMIT = sh(script: "git log --format="medium" -1 ${GIT_COMMIT}", returnStdout: true).trim()
    }
  
  stages {
    stage('Setup') {
      when {
        not {
          changeRequest()
        }
      }
      
      steps {
        script {
          echo 'Starting Setup'
          discordSend(
          title: "${DISCORD_PREFIX} Started",
          successful: true,
          result: 'ABORTED',
          thumbnail: JENKINS_HEAD,
          webhookURL: DISCORD_WEBHOOK
          )
          sh 'chmod +x gradlew'
        }
      }
    }
    
    stage('Build') {
      steps {
        echo 'Building ExampleMod'
        sh './gradlew build ${GRADLE_ARGS}'
      }
    }

    stage('Test') {
      steps {
        echo 'Testing ExampleMod'
        sh './gradlew test ${GRADLE_ARGS}'
      }
    }
  }
  
  post {
    always {
      script {
        archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true, onlyIfSuccessful: true, allowEmptyArchive: true
        
        if(env.CHANGE_ID == null) {
          discordSend(
            title: "${DISCORD_PREFIX} Finished ${currentBuild.currentResult}",
            description: '```\n ${COMMIT} \n```',
            successful: currentBuild.resultIsBetterOrEqualTo("SUCCESS"),
            result: currentBuild.currentResult,
            thumbnail: JENKINS_HEAD,
            webhookURL: DISCORD_WEBHOOK
          )
        }
      }
    }
  }
}
