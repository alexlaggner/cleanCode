import models.CrawlerInputInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.InputService;

@Component
public class App {
    @Autowired
    private InputService inputService;

    public CrawlerInputInformation getInput(){
        CrawlerInputInformation inputInformation = null;
        while(inputInformation == null){
            inputInformation = inputService.getCrawlerInputInformation();
        }
        return inputInformation;
    }
}
