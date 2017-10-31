package ru.redhat.forum.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import ru.redhat.forum.demo.gen.TestRequest;
import ru.redhat.forum.demo.gen.TestResponse;

@Component
public class ThriftServerRoute extends RouteBuilder {
    
    @Override
    public void configure() throws Exception {

        
        from("thrift://{{hello-thrift-service}}/ru.redhat.forum.demo.gen.DemoService")
            .log("Body: ${body}")
            .bean(new ThriftMessageBuilder(), "buildTestResponse");
        
        
        from("thrift://{{hello-thrift-ssl-service}}/ru.redhat.forum.demo.gen.DemoService?negotiationType=SSL&sslParameters=#sslParams")
            .log("Body: ${body}")
            .bean(new ThriftMessageBuilder(), "buildTestResponse");
    }
    
    public class ThriftMessageBuilder {
        public TestResponse buildTestResponse(TestRequest testRequest) throws UnknownHostException {
            
            return new TestResponse("Response from " + InetAddress.getLocalHost().getHostAddress(), testRequest.getId());
        }
    }
}