package com.github.lucasgmoreira.microservices.camel_microservices_a.routes.b;

import org.apache.camel.Body;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class MyFileRouter extends RouteBuilder {

    @Autowired
    private DeciderBean deciderBean;

    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .routeId("Files-Input-Route")
                .transform()
                .body(String.class)
                .choice()
                    .when(simple(("${file:ext} ends with 'xml'")))
                        .log("The file ends with xml")
//                    .when(simple("${body} contains 'USD'"))
                        .when(method(deciderBean))
                        .log("Isnt a xml file but contains USd")
                    .otherwise()
                        .log("Isnt a xml file!")
                .end()
                .log("${messageHistory} ${headers}")
                .to("file:files/output");
    }
}

@Component
class DeciderBean {

    Logger logger = LoggerFactory.getLogger(DeciderBean.class);

    public boolean isThisConditionMet (@Body String body, @Headers Map<String, String> headers) {
        logger.info("DeciderBean: {}, {}", body, headers);
        return true;
    }
}