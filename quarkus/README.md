#DIY Package the app

```shell
cd todo-quarkus

mvn clean install

podman build -f src/main/docker/Dockerfile.jvm -t quay.io/feven/todo-quarkus:latest .
podman tag quay.io/feven/todo-quarkus:latest quay.io/feven/todo-quarkus:$APP_VERSION
podman push quay.io/feven/todo-quarkus:$APP_VERSION
podman push quay.io/feven/todo-quarkus:latest   
```

#Openshift

```shell
oc apply -f manifest/todo-quarkus.yaml
oc apply -f manifest/postgres.yaml
oc apply -f  manifest/quarkus-app-config.yaml
oc create secret generic postgres-quarkus-credentials -n todo-demo    --from-literal=POSTGRES_USER=quarkus_test     --from-literal=POSTGRES_PASSWORD=quarkus_test     --from-literal=POSTGRES_DB=quarkus_test     --from-literal=POSTGRES_PORT=5432
```
