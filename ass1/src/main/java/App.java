import adapters.LoggerAdapter;
import concurrency.CrawlerThread;
import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;
import models.dto.SingleCrawlerResultDTO;
import models.enumerations.Language;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LoggerAdapter.getLogger(App.class);

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
            LoggerAdapter.logError(logger,("Could not export to .md file: "+ e.getMessage()));
        }
    }
    //Assignment 2
    public List<CrawlerInputInformation> getMultipleInputInformation(){
        return inputService.getMultipleCrawlerInputInformation();
    }

    public List<SingleCrawlerResultDTO> crawlConcurrently(List<CrawlerInputInformation> inputInformation){
        List<SingleCrawlerResultDTO> output = new LinkedList<>();
        List<CrawlerThread> threads = new LinkedList<>();

        for (CrawlerInputInformation crawlerInputInformation : inputInformation) {
            threads.add(new CrawlerThread(crawlerInputInformation,crawlerService));
        }

        for (CrawlerThread thread : threads) {
            thread.start();
        }
        for (CrawlerThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                LoggerAdapter.logError(logger,e.getMessage());
            }
        }
        for (CrawlerThread thread : threads) {
            output.add(thread.getOutputInformation());
        }

        return output;
    }
    public void exportMultiple(List<SingleCrawlerResultDTO> singleCrawlerResultDtoList){
            try {
                markDownExportService.exportMultipleCrawlerOutputinformation(singleCrawlerResultDtoList);
            }catch (IOException e){
                LoggerAdapter.logError(logger,("Could not export to .md file: "+ e.getMessage()));
            }
    }
}
