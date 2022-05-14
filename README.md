# OrderManagement-API

# Docker Build
```
docker build -t paradocx96/order-management-api:v1 .
```

# Docker Run
```
Docker run -p 8080:8080 paradocx96/order-management-api:v1
```

# Kubernetes Apply
```
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
```

# Kubernetes Delete Deployments
````
kubectl delete deployment order-management-api-deployment
````

# Kubernetes Port-Forward
```
kubectl port-forward svc/order-management-api-service 8080:8080
```

