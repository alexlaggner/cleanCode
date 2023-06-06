package concurrency;

import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;
import models.dto.SingleCrawlerResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.interfaces.CrawlerService;

import java.util.LinkedList;
import java.util.List;
public class CrawlerThread extends Thread {
    private CrawlerService crawlerService;

    private SingleCrawlerResultDTO crawlerResultDTO;

    public CrawlerThread(CrawlerInputInformation inputInformation,
                         CrawlerService crawlerService){
        this.crawlerResultDTO = new SingleCrawlerResultDTO();
        this.crawlerResultDTO.setInputInformation(inputInformation);
        this.crawlerService = crawlerService;
    }
    @Override
    public void run(){
        System.out.println( this.getName() + " starting to crawl");
        List<CrawlerOutputInformation> crawlerOutputInformation = crawlerService.crawlRecursively(this.crawlerResultDTO.getInputInformation(), "-");
        this.crawlerResultDTO.setOutputInformation(crawlerOutputInformation);
        System.out.println(this.getName() +  " finished crawling");
    }

    public SingleCrawlerResultDTO getOutputInformation(){
        return this.crawlerResultDTO;
    }
}
