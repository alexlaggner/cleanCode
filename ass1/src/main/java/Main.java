import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;
import models.enumerations.Language;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        App app = context.getBean(App.class);

        System.out.println("Welcome to the Webcrawler!\n\n");
        CrawlerInputInformation input = app.getInput();
        List<CrawlerOutputInformation> output = app.crawl(input);

        if(!input.getLanguage().equals(Language.DEFAULT)) {
            app.translateOutput(output, input.getLanguage());
        }
        app.export(input,output);
        System.out.println("ENDE\nDanke, dass Sie sich für diesen Webcrawler entschieden haben!");
    }
}
