package concurrency;

import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;
import org.springframework.beans.factory.annotation.Autowired;
import services.interfaces.CrawlerService;

import java.util.LinkedList;
import java.util.List;

public class CrawlerThread extends Thread {
    @Autowired
    private CrawlerService crawlerService;

    private List<CrawlerOutputInformation> outputInformation = new LinkedList();
    private final CrawlerInputInformation inputInformation;

    public CrawlerThread(CrawlerInputInformation inputInformation){
        this.inputInformation=inputInformation;
    }
    @Override
    public void run(){
        List<CrawlerOutputInformation> crawlerOutputInformation = crawlerService.crawlRecursively(inputInformation, "-");
        this.outputInformation = crawlerOutputInformation;
    }

    public List<CrawlerOutputInformation> getOutputInformation(){
        return this.outputInformation;
    }
}
