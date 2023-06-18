pipeline {
agent any
tools {
maven 'Maven-3.8.8'
jdk 'JDK-11.0.9'
}
stages {
stage ('Inicialización') {
steps {
sh '''
echo "PATH = ${PATH}"
echo "M2_HOME = ${M2_HOME}"
'''
}
}
/*
Se puede utilizar el siguiente código en caso de que
se quiera realizar la descarga de código fuente del
proyecto sin utilizar el plugin de github:
stage ('Descarga codigo') {
steps {
git branch: 'master',
credentialsId: 'github',
url: 'git@github.com:loulirsal/directorio.git'
}
}
*/
stage('Compilación - Maven') {
steps {
sh 'mvn clean compile'
}
}
stage('Test unitarios - Junit') {
steps {
sh 'mvn test'
}
post {
success {
junit 'target/surefire-reports/**/*.xml'
}
}
}
stage('Análisis estático - Sonar') {
steps {
sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.1.25:9000 -Dsonar.login=squ_0e011f98d3ab0552489ba3ba674fc625c4ff835d'

}
}
//stage('Generación war y subida - Artifactory') {
//steps {
//sh 'mvn --settings settings.xml deploy -Dmaven.test.skip'
//}
//}
stage('Aprobación para despliegue') {
steps {
input "¿Se aprueba el despliegue?"
}
}
//stage('Despliegue con Ansible') {
//steps {
//ansiColor('xterm') {
//ansiblePlaybook(
//playbook: 'playbook-directorio.yml',
//inventory: '/etc/ansible/hosts',
//credentialsId: 'ansible',
//colorized: true)
//}
//}
//}
}
}