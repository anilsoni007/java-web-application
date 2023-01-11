node {
buildName 'pipeline-${BUILD_NUMBER}'
def mavenHome = tool name: "maven3.8.7"
properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '5')), [$class: 'JobLocalConfiguration', changeReasonComment: ''], pipelineTriggers([pollSCM('* * * * *')])])

stage('CheckoutCode')
{
checkout scmGit(branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[credentialsId: 'githubid', url: 'https://github.com/anilsoni007/java-web-application.git']])
}
stage('Build')
{
sh "${mavenHome}/bin/mvn clean package"
}
stage('ExecuteSQReport'){
sh "${mavenHome}/bin/mvn sonar:sonar"
}
stage('UploadArtifacttoNexus'){
 sh "${mavenHome}/bin/mvn deploy"
}
stage('DeployToTomcat'){
sshagent(['ssh_auth']) {
sh "scp -o StrictHostKeyChecking=no target/maven-web-application.war ec2-user@13.234.119.149:/opt/apache-tomcat-9.0.70/webapps/"
}
}
stage('SendEmailNotification'){
emailext body: '''Build over

Regards
Anil Soni
DevOps Engineer''', subject: 'BuildOver', to: 'x1@gmail.com'
}
}
