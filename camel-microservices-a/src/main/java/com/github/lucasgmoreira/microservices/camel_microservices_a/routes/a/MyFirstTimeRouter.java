package com.github.lucasgmoreira.microservices.camel_microservices_a.routes.a;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
public class MyFirstTimeRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getGurrentTimeBean;

    @Autowired
    private SimpleLoggerProcessingComponent loggingComponent;

    @Override
    public void configure() throws Exception {
        from("timer:first-timer")
                // .transform()
                // .constant("Time now is: " + LocalDateTime.now())
                .log("${body}") // null
                .transform().constant("My constant message")
                .log("${body}") // My constant message
                .bean(getGurrentTimeBean, "getCurrentTime")
                .log("${body}") // time is now
                .bean(loggingComponent)
                .log("${body}")
                .process(new SimpleLoggingProcessor())
                .to("log:first-time"); // database
    }
}


@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time is now: " + LocalDateTime.now();
    }
}


@Component
class SimpleLoggerProcessingComponent {
    private Logger logger = LoggerFactory.getLogger(SimpleLoggerProcessingComponent.class);

    public void process(String message) {
        logger.info("SimpleLoggerProcessingComponent {}", message);
    }
}


class SimpleLoggingProcessor implements Processor {
    private Logger logger = LoggerFactory.getLogger(SimpleLoggerProcessingComponent.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());
    }
}