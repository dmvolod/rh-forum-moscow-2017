# This is a demo of the Camel gRPC service for standalone or OpenShift deployment options

### How to run as standalone Spring Boot service

cd grpc-sb-camel-server folder
 
./mvnw clean package spring-boot:run -Drun.profile=dev

cd grpc-sb-camel-client folder

./mvnw clean package spring-boot:run -Drun.profile=dev

### How to run both (all in one) server and client on OpenShift (minishift) instance

1. minishift start

2. oc login 

3. oc new-project rh-forum-camel-demo

4. cd grpc-sb-camel-server folder

./mvnw clean package fabric8:deploy

5. cd grpc-sb-camel-client folder

./mvnw clean package fabric8:deploy

### How to run server on OpenShift (minishift) instance and client on other host
Please note, that remote service routing on OpenShift (minishift) supports TLS passthrough for HTTP/2  

1. minishift start

2. oc login 

3. oc new-project rh-forum-camel-demo

4. cd grpc-sb-camel-server folder

./mvnw clean package fabric8:deploy

5. cd grpc-sb-camel-client folder

./mvnw clean package spring-boot:run -Drun.profiles=ose-remote


oc create -f https://raw.githubusercontent.com/jboss-fuse/application-templates/master/fis-image-streams.json
oc create -f https://raw.githubusercontent.com/dmvolod/rh-forum-moscow-2017/master/grpc-sb-camel-server/ocp-template/fis2-grpc-server-template.yml
oc import-image fis-java-openshift --all

oc secrets new jolokia-pw jolokia-pw-secret
oc new-app --template=grpc-sb-camel-server