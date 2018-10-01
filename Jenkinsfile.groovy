@Library('semantic_releasing') _

podTemplate(label: 'mypod', containers: [
        containerTemplate(name: 'maven', image: 'maven:3.5.2-jdk-8', command: 'cat', ttyEnabled: true)
],
        volumes: [
                hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
        ]) {
    node('mypod') {

        stage('checkout') {
            git url: 'https://github.com/adessoSchweiz/belimo-mobile'
        }

        stage('install appium') {
            sh 'yum install appium -y'
        }

        stage('system tests') {
            container('maven') {
                sh "mvn clean integration-test failsafe:integration-test failsafe:verify"
            }
            junit allowEmptyResults: true, testResults: '**/target/failsafe-reports/TEST-*.xml'
        }
    }
}
