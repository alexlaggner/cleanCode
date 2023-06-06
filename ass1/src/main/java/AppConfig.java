import concurrency.CrawlerThread;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import services.impl.*;
import services.interfaces.*;

@Configuration
public class AppConfig {
    @Bean
    public InputService inputService() {
        return new InputServiceImpl();
    }
    @Bean
    public HtmlDataService htmlParsingService(){
        return new HtmlDataServiceImpl();
    }
    @Bean
    public CrawlerService crawlerService(){
        return new CrawlerServiceImpl();
    }
    @Bean
    public TranslationService translationService(){return new TranslationServiceImpl(); }
    @Bean
    public MarkDownExportService markDownExportService(){return new MarkDownExportServiceImpl();
    }
    @Bean
    public App app(){
        return new App();
    }
}
