def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'DockerHubUserPass', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t ahmedasifcs/javaapp-maven .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push ahmedasifcs/javaapp-maven'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
