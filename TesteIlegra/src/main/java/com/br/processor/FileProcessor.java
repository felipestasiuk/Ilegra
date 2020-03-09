package com.br.processor;

import com.br.service.FileService;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileProcessor implements Processor {

    @Autowired
    private FileService fileService;

    @Override
    public void process(Exchange exchange) throws Exception {
        Message message = exchange.getIn();
        File fileToProcess = message.getBody(File.class);

        LineIterator it = FileUtils.lineIterator(fileToProcess, "UTF-8");
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                if(line.length() > 3) {
                    String tipo = line.substring(0, 3);

                    if ("001".equals(tipo)) {
                        fileService.createSalesman(line);
                    } else if ("002".equals(tipo)) {
                        fileService.createCustomer(line);
                    } else if ("003".equals(tipo)) {
                        fileService.createSale(line);
                    }
                }
            }
            fileService.creteOutFile();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            LineIterator.closeQuietly(it);
        }
    }
}
