package concurrency;

import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.interfaces.CrawlerService;

import java.util.LinkedList;
import java.util.List;
public class CrawlerThread extends Thread {
    private CrawlerService crawlerService;

    private List<CrawlerOutputInformation> outputInformation = new LinkedList();
    private final CrawlerInputInformation inputInformation;

    public CrawlerThread(CrawlerInputInformation inputInformation,
                         CrawlerService crawlerService){
        this.inputInformation=inputInformation;
        this.crawlerService = crawlerService;
    }
    @Override
    public void run(){
        System.out.println( this.getName() + " starting to crawl");
        List<CrawlerOutputInformation> crawlerOutputInformation = crawlerService.crawlRecursively(inputInformation, "-");
        this.outputInformation = crawlerOutputInformation;
        System.out.println(this.getName() +  " finished crawling");
    }

    public List<CrawlerOutputInformation> getOutputInformation(){
        return this.outputInformation;
    }
}
