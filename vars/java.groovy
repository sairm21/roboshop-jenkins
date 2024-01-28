def call() {
    pipeline {

        agent {
            node { label 'Workstation' }
        }


        stages {
            stage('Build') {
                steps {
                    sh "mvn package"
                }
            }

            stage('Unit tests') {
                steps {
                    echo "unit tests"
                    // sh "mvn test"
                }
            }

            stage('Code Analaysis') {
                steps {
                    echo "code analasys"
                    //sh 'sonar-scanner -Dsonar.host.url=http://172.31.47.19:9000 -Dsonar.login="admin" -Dsonar.password="S@ir&m1221" -Dsonar.projectKey=shipping -Dsonar.java.binaries=target -Dsonar.qualitygate.wait=true'
                }
            }

            stage('Security Scans') {
                steps {
                    echo "security scans"
                }
            }

            stage('Publish a Artifact') {
                steps {
                    echo "publish artifacts"
                }
            }
        }
    }
}