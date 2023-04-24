import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;
import models.enumerations.Language;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    /*
    input the URL, the depth of websites to crawl, and the target language to the command line
    create a compact overview of the crawled websites in a specified target language (e.g. translate it into German)
        record and translate only the headings
        represent the depth of the crawled websites with proper indentation (see example)
        record the URLs of the crawled sites
        highlight broken links
    find the links to other websites and recursively do the analysis for those websites (it is enough if you analyze the pages at a depth of 2 without visiting further links, you might also allow the user to configure this depth via the command line)
    store the results in a single markdown file (.md extension
     */
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
        int end = 0;

    }
}
