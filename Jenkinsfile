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

    stage('Aprobación para despliegue') {
        steps {
            input "¿Se aprueba el despliegue?"
        }
    }

    }
}