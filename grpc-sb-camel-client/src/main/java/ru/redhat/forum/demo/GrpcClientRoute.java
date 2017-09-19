package ru.redhat.forum.demo;

import java.util.Random;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import ru.redhat.forum.demo.gen.TestRequest;
import ru.redhat.forum.demo.gen.TestResponse;

@Component
public class GrpcClientRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:foo?period=2000")
            .bean(new GrpcMessageBuilder(), "buildTestRequest")
            .to("grpc://{{hello-grpc-service}}/ru.redhat.forum.demo.gen.DemoService?method=TestCall")            
            .log("Body: ${body}");
    }
    
    public class GrpcMessageBuilder {
        public TestRequest buildTestRequest() {
            return TestRequest.newBuilder().setName("Hello").setId((new Random()).nextInt(100)).build();
        }
    }

}