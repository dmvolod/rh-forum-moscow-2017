package ru.redhat.forum.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import ru.redhat.forum.demo.gen.TestRequest;
import ru.redhat.forum.demo.gen.TestResponse;

@Component
public class GrpcServerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("grpc://{{hello-grpc-service}}/ru.redhat.forum.demo.gen.DemoService")
            .log("Body: ${body}")
            .bean(new GrpcMessageBuilder(), "buildTestResponse");
    }
    
    public class GrpcMessageBuilder {
        public TestResponse buildTestResponse(TestRequest testRequest) throws UnknownHostException {
            return TestResponse.newBuilder().setName("Response from " + InetAddress.getLocalHost().getHostAddress()).setId(testRequest.getId()).build();
        }
    }
}