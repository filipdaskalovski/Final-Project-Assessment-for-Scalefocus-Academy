pipeline {
     agent any
     environment {
       KUBECONFIG = '/Users/filip/.kube/config'
     }
     stages {
       stage('Verification') {
         steps {
           script {
             def deploymentExists = bat(script: 'kubectl get deployment final-project-wp-scalefocus -n wp', returnStatus: true) == 0
             if (deploymentExists) {
               echo 'WordPress is already installed'
             } else {
               echo 'WordPress is not installed. Proceed with the deployment.'
             }
           }
         }
       }
       stage('Deployment of Code') {
         steps {
           script {
             try {
               def namespaceExists = bat(script: 'kubectl get namespace wp', returnStatus: true) == 0
               if (!namespaceExists) {
                 bat 'kubectl create namespace wp'
               }
               bat 'helm upgrade --install final-project-wp-scalefocus /Users/filip/Desktop/SCALEFOCUS/FINAL/clone/bitnami/wordpress -n wp -f /Users/filip/Desktop/SCALEFOCUS/FINAL/clone/bitnami/wordpress/values.yaml'

               bat 'kubectl port-forward --namespace wp svc/final-project-wp-scalefocus-wordpress 8416:80'


             } catch (Exception e) {
               echo "Deployment error: ${e.getMessage()}"
               error "Deployment failed: ${e.getMessage()}"
             }
           }
         }
       }
     }
   }