def call() {
    node('Workstation') {

             if (env.cibuild == "java") {
                stage('Build') {
                         sh "mvn package"
                 }
            }
        if (env.cibuild == "nodejs") {
            stage('Build') {
                sh "npm i"
            }
        }


            stage('Unit tests') {
                     echo "unit tests"
                    // sh "npm test"
             }

            stage('Code Analaysis') {
                     echo "code analasys"
                    // sh 'sonar-scanner -Dsonar.host.url=http://172.31.47.19:9000 -Dsonar.login="admin" -Dsonar.password="S@ir&m1221" -Dsonar.projectKey=cart -Dsonar.qualitygate.wait=true'
             }

            stage('Security Scans') {
                     echo "security scans"
             }

        if (env.TAG_NAME ==~ ".*")
        stage('Publish a Artifact') {
                echo "publish artifacts"
             }
        }
    }