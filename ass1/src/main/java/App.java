import models.CrawlerInputInformation;
import models.CrawlerOutputInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.CrawlerService;
import services.HtmlDataService;
import services.InputService;

import java.util.List;

@Component
public class App {
    @Autowired
    private InputService inputService;

    @Autowired
    private CrawlerService crawlerService;

    public CrawlerInputInformation getInput(){
        CrawlerInputInformation inputInformation = null;
        while(inputInformation == null){
            inputInformation = inputService.getCrawlerInputInformation();
        }
        return inputInformation;
    }

    public List<CrawlerOutputInformation> crawl(CrawlerInputInformation inputInformation) {
        return crawlerService.crawlRecursively(inputInformation, "-");
    }
}
