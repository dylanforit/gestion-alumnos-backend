pipeline {
    agent any
    tools {
        maven 'Maven-3.8.8'
        jdk 'JDK-11.0.9'
    }
    stages {
	    stage('Inicio del Job') {
	      steps {
	        script {
	          slackSend message: "El Job ha comenzado su ejecución  - ${env.JOB_NAME} - Ejecución ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)", color: 'warning'
	        }
	      }
	    }
    
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
	            sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.1.25:9000 -Dsonar.login=squ_ce1b0f3c6d5552b2e142e7fedcd233f7b86670cb'
	        }
	    }
	
	    stage('Aprobación para despliegue') {
	        steps {
	        	script {
	        		slackSend(message: "Despliegue pendiente de aprobación", color: 'Warning')
	      		}
	            input "¿Se aprueba el despliegue?"
	        }
	    }

    }
      post {
	    always {
	      script {
	        slackSend(message: "El Job ha finalizado", color: 'good')
	      }
	    }
	
	    success {
	      script {
	        slackSend(message: "El Job se ha ejecutado exitosamente", color: 'good')
	      }
	    }
	
	    failure {
	      script {
	        slackSend(message: "El Job ha fallado", color: 'danger')
	      }
	    }
	  }
}