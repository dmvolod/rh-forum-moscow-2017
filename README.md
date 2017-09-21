# This is a demo of the Camel gRPC service for standalone or OpenShift deployment options

### How to run as standalone Spring Boot service

cd grpc-sb-camel-server folder
 
./mvnw clean package spring-boot:run -Drun.profile=dev

cd grpc-sb-camel-client folder

./mvnw clean package spring-boot:run -Drun.profile=dev

### How to run on OpenShift (minishift) instance

1. minishift start

2. oc login 

3. oc new-project rh-forum-camel-demo

4. ./mvnw clean package fabric8:deploy