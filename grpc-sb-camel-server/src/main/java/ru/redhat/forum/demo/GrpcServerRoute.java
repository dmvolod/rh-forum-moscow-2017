package ru.redhat.forum.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import ru.redhat.forum.demo.gen.TestRequest;
import ru.redhat.forum.demo.gen.TestResponse;

@Component
public class GrpcServerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("grpc://{{hello-grpc-service}}/ru.redhat.forum.demo.gen.DemoService")
            .bean(new GrpcMessageBuilder(), "buildTestResponse")
            .log("Body: ${body}");
    }
    
    public class GrpcMessageBuilder {
        public TestResponse buildTestResponse(TestRequest testRequest) {
            return TestResponse.newBuilder().setName(testRequest.getName() + " World!!!").setId(testRequest.getId()).build();
        }
    }
}
