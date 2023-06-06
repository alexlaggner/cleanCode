import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;
import models.dto.SingleCrawlerResultDTO;
import models.enumerations.Language;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        App app = context.getBean(App.class);

        System.out.println("Welcome to the Webcrawler!\n\n");
        //------------ Ass1
       /* CrawlerInputInformation input = app.getInput();
        List<CrawlerOutputInformation> output = app.crawl(input);
        if(!input.getLanguage().equals(Language.DEFAULT)) {
            app.translateOutput(output, input.getLanguage());
        }
        app.export(input,output);*/

        //------------ Ass2
        List<CrawlerInputInformation> inputInformation = app.getMultipleInputInformation();
        List<SingleCrawlerResultDTO> singleCrawlerResultDTOList = app.crawlConcurrently(inputInformation);

        for (SingleCrawlerResultDTO singleCrawlerResultDTO : singleCrawlerResultDTOList) {
            Language language = singleCrawlerResultDTO.getInputInformation().getLanguage();
            if(language != null && !language.equals(Language.DEFAULT)){
                app.translateOutput(singleCrawlerResultDTO.getOutputInformation(), language);
            }
        }
        app.exportMultiple(singleCrawlerResultDTOList);
        System.out.println("ENDE\nDanke, dass Sie sich für diesen Webcrawler entschieden haben!");
    }
}
