package com.github.lucasgmoreira.microservices.camel_microservices_a.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMqSenderRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//        from("timer:active-mq-timer?period=10000")
//                .transform()
//                .constant("My message for activemq")
//                .log("${body}")
//                .to("activemq:my-activemq-queue");

//        from("file:files/json")
//                .log("${body}")
//                .to("activemq:my-activemq-queue");
        System.out.println("hgere");
        from("file:files/xml")
                .log("here ${body}")
                .to("activemq:my-activemq-xml-queue");

    }
}
