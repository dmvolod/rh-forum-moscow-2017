package ru.redhat.forum.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class GrpcServerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("grpc://localhost:1101/ru.redhat.forum.demo.gen.DemoService").log("Body: ${body}");
    }

}
