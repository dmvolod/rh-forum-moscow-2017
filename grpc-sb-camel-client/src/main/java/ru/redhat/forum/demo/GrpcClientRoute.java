package ru.redhat.forum.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Random;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ru.redhat.forum.demo.gen.TestRequest;

@Component
public class GrpcClientRoute extends RouteBuilder {
    
    @Autowired
    private Environment environment;

    @Override
    public void configure() throws Exception {
        if (Arrays.asList(environment.getActiveProfiles()).contains("ose-remote")) {
            from("timer:foo?period=2000")
                .bean(new GrpcMessageBuilder(), "buildTestRequest")
                .to("grpc://{{hello-grpc-service}}/ru.redhat.forum.demo.gen.DemoService?method=TestCall&negotiationType=TLS&keyCertChainResource=classpath:certs/client.pem&" 
                    + "keyResource=classpath:certs/client.key&trustCertCollectionResource=classpath:certs/ca.pem")
                .log("Body: ${body}");
        } else {
            from("timer:foo?period=2000")
                .bean(new GrpcMessageBuilder(), "buildTestRequest")
                .to("grpc://{{hello-grpc-service}}/ru.redhat.forum.demo.gen.DemoService?method=TestCall")            
                .log("Body: ${body}");
        }
    }
    
    public class GrpcMessageBuilder {
        public TestRequest buildTestRequest() throws UnknownHostException {
            return TestRequest.newBuilder().setName("Hello from ip:" + InetAddress.getLocalHost().getHostAddress()).setId((new Random()).nextInt(100)).build();
        }
    }

}
