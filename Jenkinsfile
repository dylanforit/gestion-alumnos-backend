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
    
        stage ('Definición de variables') {
            steps {
                sh '''
                echo "PATH = ${PATH}"
                echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

	    stage('Compilación - Maven') {
	        steps {
	            sh 'mvn clean package'
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
	    stage ('OWASP Dependency-Check Vulnerabilities') {  
		    steps {  
		      sh 'mvn dependency-check:check'
		   
		     dependencyCheckPublisher pattern: 'target/dependency-check-report.xml'  
		    }  
		}  
        stage('Análisis estático - Sonar') {  // Etapa para realizar el análisis estático con SonarQube
            steps {
                script {
                    scannerHome = tool 'Sonarqube Scanner IC'  // Utilizar la herramienta de SonarQube configurada en Jenkins
                }
                withSonarQubeEnv('Sonarqube IC') {  // Configurar el entorno de SonarQube
                    sh "${scannerHome}/bin/sonar-scanner"  // Ejecutar el escaneo con SonarQube
                }
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