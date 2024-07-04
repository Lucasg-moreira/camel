package com.github.lucasgmoreira.microservices.camel_microservices_b.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqReceiver extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:my-activemq-queue")
                .to("log:receiver-message-from-active-mq");
    }
}
