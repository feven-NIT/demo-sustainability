oc get -o jsonpath='{.status.infrastructureName}{"\n"}' infrastructure cluster

oc get configmap/coreos-bootimages -n openshift-machine-config-operator -o jsonpath='{.data.stream}' | jq -r '.architectures.<arch>.images.aws.regions."<region>".image'

#oc get configmap/coreos-bootimages -n openshift-machine-config-operator -o jsonpath='{.data.stream}' | jq -r '.architectures.aarch64.images.aws.regions."us-east-2".image'

https://docs.openshift.com/container-platform/4.14/post_installation_configuration/multi-architecture-configuration.html
