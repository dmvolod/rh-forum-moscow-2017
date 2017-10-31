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
public class ThriftClientRoute extends RouteBuilder {
    
    @Autowired
    private Environment environment;

    @Override
    public void configure() throws Exception {
        
        if (Arrays.asList(environment.getActiveProfiles()).contains("ose-remote")) {
            from("timer:foo?period=2000")
                .bean(new ThriftMessageBuilder(), "buildTestRequest")
                .to("thrift://{{hello-thrift-service}}/ru.redhat.forum.demo.gen.DemoService?method=TestCall")            
                .log("Body: ${body}");
        } else {
            from("timer:foo?period=2000")
                .bean(new ThriftMessageBuilder(), "buildTestRequest")
                .to("thrift://{{hello-thrift-service}}/ru.redhat.forum.demo.gen.DemoService?method=TestCall")            
                .log("Body: ${body}");
        }
        
        /*
        from("timer:foo?period=2000")
        .bean(new ThriftMessageBuilder(), "buildTestRequest")
        .to("thrift://localhost:2080/ru.redhat.forum.demo.gen.DemoService?method=TestCall")            
        .log("Body: ${body}");
        */
    }
    
    public class ThriftMessageBuilder {
        public TestRequest buildTestRequest() throws UnknownHostException {
            return new TestRequest("Hello from ip:" + InetAddress.getLocalHost().getHostAddress(), new Random().nextInt(100));
        }
    }

}
