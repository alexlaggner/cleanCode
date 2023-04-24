package services.impl;

import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import services.interfaces.MarkDownExportService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class MarkDownExportServiceImpl implements MarkDownExportService {

    private static final String PATH ="out.md";
    public void exportCrawlerOutputInformation(CrawlerInputInformation inputInformation,List<CrawlerOutputInformation> outputInformation) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(PATH));

        writer.println("INPUT:");
        writer.println("URL: " + inputInformation.getUrl() );
        writer.println("TIEFE:" + inputInformation.getDepth());
        writer.println("ZIELSPRACHE: " +inputInformation.getLanguage().getNameDe());

        writer.println("________________________________");


        for (CrawlerOutputInformation crawlerOutputInformation : outputInformation) {
            writer.println("URL: "+crawlerOutputInformation.getUrl());
            writer.println("TIEFE: "+crawlerOutputInformation.getDepth());
            writer.println("VORHERGEHENDER LINK: " + crawlerOutputInformation.getPreviousUrl());
            writer.println("HEADERS:");

            if(crawlerOutputInformation.isBrokenLink()){
                writer.println("BROKEN LINK");
            }
            else {
                for (String header : crawlerOutputInformation.getHeaders()) {
                    writer.println(header);
                }
            }
            writer.println("________________________________");
        }
        writer.close();
    }
}
