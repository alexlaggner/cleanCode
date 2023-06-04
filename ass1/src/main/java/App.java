import concurrency.CrawlerThread;
import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;
import models.enumerations.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.interfaces.CrawlerService;
import services.interfaces.InputService;
import services.interfaces.MarkDownExportService;
import services.interfaces.TranslationService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class App {
    @Autowired
    private InputService inputService;

    @Autowired
    private CrawlerService crawlerService;

    @Autowired
    private TranslationService translationService;

    @Autowired
    private MarkDownExportService markDownExportService;

    private static final Logger logger = LogManager.getLogger(App.class);

    public CrawlerInputInformation getInput(){
        CrawlerInputInformation inputInformation = null;
        while(inputInformation == null){
            inputInformation = inputService.getCrawlerInputInformation();
        }
        return inputInformation;
    }

    public void translateOutput(List<CrawlerOutputInformation> outputInformationList, Language language){
        for (CrawlerOutputInformation crawlerOutputInformation : outputInformationList) {
            List<String> translatedHeaders = translationService.translateHeadings(crawlerOutputInformation.getHeaders(), language);
            crawlerOutputInformation.setHeaders(translatedHeaders);
        }

    }
    public List<CrawlerOutputInformation> crawl(CrawlerInputInformation inputInformation) {
        return crawlerService.crawlRecursively(inputInformation, "-");
    }
    public void export(CrawlerInputInformation inputInformation,
                       List<CrawlerOutputInformation> outputInformation){
        try {
            markDownExportService.exportCrawlerOutputInformation(inputInformation,outputInformation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Assignment 2
    public List<CrawlerInputInformation> getMultipleInputInformation(){
        return inputService.getMultipleCrawlerInputInformation();
    }

    public List<CrawlerOutputInformation> crawlConcurrently(List<CrawlerInputInformation> inputInformation){
        List<CrawlerOutputInformation> output = new LinkedList<>();
        List<CrawlerThread> threads = new LinkedList<>();

        for (CrawlerInputInformation crawlerInputInformation : inputInformation) {
            threads.add(new CrawlerThread(crawlerInputInformation));
        }

        for (CrawlerThread thread : threads) {
            thread.start();
        }
        for (CrawlerThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
        for (CrawlerThread thread : threads) {
            output.addAll(thread.getOutputInformation());
        }

        return output;
    }
}
