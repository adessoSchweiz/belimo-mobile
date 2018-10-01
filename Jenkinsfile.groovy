@Library('semantic_releasing') _

podTemplate(label: 'mypod', containers: [
        containerTemplate(name: 'docker', image: 'docker', ttyEnabled: true, command: 'cat'),
        containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl:v1.11.2', command: 'cat', ttyEnabled: true),
        containerTemplate(name: 'npm-jdk', image: 'khinkali/npm-java:0.0.4', ttyEnabled: true, command: 'cat'),
],
        volumes: [
                hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
        ]) {
    node('mypod') {

        stage('checkout') {
            git url: 'https://github.com/adessoSchweiz/belimo-mobile'
        }

        stage('system tests') {
            container('maven') {
                sh "mvn clean integration-test failsafe:integration-test failsafe:verify"
            }
            junit allowEmptyResults: true, testResults: '**/target/failsafe-reports/TEST-*.xml'
        }
    }
}
