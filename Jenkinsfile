def CONTAINER_NAME="app-pipeline-332488"
def CONTAINER_TAG="latest"
def DOCKER_HUB_USER="debjyotidas"
def HTTP_PORT="8090"


node {

        def app_name = 'app-pipeline-332488'
        def app_dockerfile_name = 'Dockerfile'
        def app_container_name = 'app-pipeline-332488'
        def app_tag="latest"


    stage('Initialize'){
        def dockerHome = tool 'myDocker'
        def mavenHome  = tool 'myMaven'
        env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
    }

    stage('Checkout') {
        checkout scm
    }

    stage('Selenium Test'){
    	try {
        	sh "mvn clean test"
	}
	catch (exc) {
		echo "there are test failures. trying to rollback deployment."
		sh("curl -LO https://storage.googleapis.com/kubernetes-release/release/\$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl")
                sh("chmod +x ./kubectl")
		sh("./kubectl rollout undo deployment app-pipeline-332488 -n debjyoti")
	}
    }


}
