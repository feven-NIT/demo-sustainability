#DIY Package the app

```shell
cd todo-spring

mvn clean install

podman build -f src/main/docker/Dockerfile.jvm -t quay.io/feven/todo-spring:latest .
podman tag quay.io/feven/todoapp:latest quay.io/feven/todo-spring:$APP_VERSION
podman push quay.io/feven/todo-spring:$APP_VERSION
podman push quay.io/feven/todo-spring:latest   
```

#Openshift

```shell
oc apply -f manifest/todo-spring.yaml
oc apply -f manifest/postgres.yaml
oc apply -f  manifest/spring-app-config.yaml
oc create secret generic postgres-spring-credentials -n todo-demo    --from-literal=POSTGRES_USER=quarkus_test     --from-literal=POSTGRES_PASSWORD=quarkus_test     --from-literal=POSTGRES_DB=quarkus_test     --from-literal=POSTGRES_PORT=5432
```
