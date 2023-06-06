package services.impl;

import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;
import models.dto.SingleCrawlerResultDTO;
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

        printInput(writer,inputInformation);

        for (CrawlerOutputInformation crawlerOutputInformation : outputInformation) {
            printOutput(writer,crawlerOutputInformation);
        }
        writer.close();
    }

    @Override
    public void exportMultipleCrawlerOutputinformation(List<SingleCrawlerResultDTO> singleCrawlerResultDTOList) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(PATH));
        for (SingleCrawlerResultDTO singleCrawlerResultDTO : singleCrawlerResultDTOList) {
            writer.println("____________NEXT_CRAWLERRESULT____________________");
            printInput(writer,singleCrawlerResultDTO.getInputInformation());
            for (CrawlerOutputInformation crawlerOutputInformation : singleCrawlerResultDTO.getOutputInformation()) {
                printOutput(writer,crawlerOutputInformation);
            }
        }
        writer.close();
    }

    private void printInput(PrintWriter writer, CrawlerInputInformation inputInformation){
        writer.println("INPUT:");
        writer.println("URL: " + inputInformation.getUrl() );
        writer.println("TIEFE:" + inputInformation.getDepth());
        writer.println("ZIELSPRACHE: " +inputInformation.getLanguage().getNameDe());

        writer.println("________________________________");
    }
    private void printOutput(PrintWriter writer, CrawlerOutputInformation outputInformation){
        writer.println("URL: "+outputInformation.getUrl());
        writer.println("TIEFE: "+outputInformation.getDepth());
        writer.println("VORHERGEHENDER LINK: " + outputInformation.getPreviousUrl());
        writer.println("HEADERS:");

        if(outputInformation.isBrokenLink()){
            writer.println("BROKEN LINK");
        }
        else {
            for (String header : outputInformation.getHeaders()) {
                writer.println(header);
            }
        }
        writer.println("________________________________");
    }
}
