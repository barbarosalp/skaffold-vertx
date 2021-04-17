# hello Skaffold
Sample spring boot application that runs with **skaffold**, using **jib** to build docker image.  

---
Install `minikube`  
https://minikube.sigs.k8s.io/docs/start/

Start the k8s cluster;
```
minikube start
```

Run the application with;
```
skaffold dev --port-forward
```

