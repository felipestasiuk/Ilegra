package com.br.route;

import com.br.processor.FileProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamelRouteBuilder extends RouteBuilder {

    @Autowired
    private FileProcessor fileProcessor;

    @Override
    public void configure() throws Exception {
        from("file:data/in")
                .process(fileProcessor)
                .to("file:data/processed");
    }
}
