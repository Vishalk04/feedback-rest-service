node {
    def app;
    def namespace = 'hc-feedback';

    stage('Clone repository') {
        /* Let's make sure we have the repository cloned to our workspace */

        checkout scm
    }

    stage('Build image') {
       sh 'mvn clean install'
        
    
        /* This builds the actual image; synonymous to
         * docker build on the command line */

       app = docker.build("kartikjalgaonkar/hc_feedback_pipeline1")
    }

    stage('Push image') {
        /* Finally, we'll push the image with two tags:
         * First, the incremental build number from Jenkins
         * Second, the 'latest' tag.
         * Pushing multiple tags is cheap, as all the layers are reused. */
        docker.withRegistry('https://registry.hub.docker.com', 'docker_credentials') {
       // app.push("${env.BUILD_NUMBER}")
        app.push("latest")
        }
    }
    
    stage('kubectl deploy'){
             
       
        switch (namespace) {
            case "hc-feedback":
                sh("kubectl get ns ${namespace} --kubeconfig=/home/yash/.kube/config || kubectl create namespace ${namespace} --kubeconfig=/home/yash/.kube/config")
                sh("kubectl --namespace=${namespace} apply -f deployment.yml --kubeconfig=/home/yash/.kube/config")
                sh("kubectl --namespace=${namespace} apply -f service.yml --kubeconfig=/home/yash/.kube/config")
                sh ("kubectl --namespace=${namespace} apply -f ingress.yml --kubeconfig=/home/yash/.kube/config")
                sh ("kubectl  apply --record -f hc-feedback-hpa.yaml --kubeconfig=/home/yash/.kube/config")
             //   sh("echo http://`kubectl --namespace=${namespace} get service/${feSvcName} --output=json --kubeconfig=/home/yash/.kube/config | jq -r '.status.loadBalancer.ingress[0].ip'` > ${feSvcName} ")
            
                break
        }
    }
   
}
