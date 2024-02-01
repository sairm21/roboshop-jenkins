def call() {
    node('Workstation') {

        stage('code checkout'){

            sh 'ls -A1 | xargs rm -rf'

            if (env.TAG_NAME ==~ ".*") {
                env.gitbranch = "refs/tags/${env.TAG_NAME}"
            } else {
                env.gitbranch = "${env.BRANCH_NAME}"
            }
                checkout scm: [$class: 'GitSCM', userRemoteConfigs: [[url: "https://github.com/sairm21/${env.component}"]], branches: [[name: gitbranch]]],poll: false
            }

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

        if (env.TAG_NAME ==~ ".*"){
            stage('Publish a Artifact') {
                echo "publish artifacts"

                sh 'rm -rf Jenkinsfile'

                sh 'echo ${TAG_NAME} > version'

                sh 'zip -r ${component}-${TAG_NAME}.zip'

                sh 'curl -v -u admin:S@ir&m1221 --upload-file ${component}-${TAG_NAME}.zip http://172.31.15.226:8081/repository/${component}-${TAG_NAME}.zip'
            }
        }
        }
    }