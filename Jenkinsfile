pipeline {
    agent any

    parameters {
        choice(
            name: 'SUITE',
            choices: ['uiErrorValidation', 'HybridE2ETest', 'API_ErrorValidation', 'Login', 'Registration'],
            description: 'Select which test suite to run'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Selected Test Suite') {
            environment {
                // This makes API_TOKEN available as environment variable
                API_TOKEN = credentials('token')
            }
            steps {
                // Use double quotes for Windows batch to pass environment variable
                bat "mvn clean test -P%SUITE% -Dapi.token=\"%API_TOKEN%\""
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/test-output/**/*', allowEmptyArchive: true
        }
    }
}