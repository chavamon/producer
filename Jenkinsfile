pipeline {
    agent any

    environment {
        // Define environment variables if needed
        MAVEN_HOME = '/opt/homebrew/Cellar/maven/3.9.8/libexec'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checks out the source code from your SCM
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Use Maven to compile the project
                sh '${MAVEN_HOME}/bin/mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Run tests using Maven
                sh '${MAVEN_HOME}/bin/mvn test'
            }
            post {
                // Handle test results here (e.g., archive artifacts, junit reports)
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Deploy') {
            steps {
                // Assuming SSH-based deployment
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: 'SSHServerConfig', // Define this in Jenkins configuration
                            transfers: [
                                sshTransfer(
                                    sourceFiles: '**/target/*.jar', // Adjust based on your artifact's location
                                    removePrefix: 'target',
                                    remoteDirectory: '/path/to/deployment/directory', // Destination on the server
                                    execCommand: 'restart-service-command' // Command to restart your service
                                )
                            ]
                        )
                    ]
                )
            }
        }
    }

    stage('Deploy to EC2') {
        steps {
            // Assuming you have a packaged jar in the target directory
            // Replace 'your-ec2-user' with your actual EC2 instance user
            // Replace 'your-ec2-host' with your actual EC2 instance public DNS or IP
            // Replace '/path/to/your/application.jar' with the actual path on your EC2 instance
            sh 'scp target/*.jar ec2-user@your-ec2-host:/path/to/your/application.jar'
            sh 'ssh ec2-user@your-ec2-host "sudo systemctl restart your-application-service"'
        }
    }


    post {
        // Post actions like cleanup, notifications, etc.
        always {
            echo 'Build, Test, and Deploy stages completed!'
        }
    }
}

