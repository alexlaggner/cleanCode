import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import services.*;

@Configuration
public class AppConfig {
    @Bean
    public InputService inputService() {
        return new InputServiceImpl();
    }
    @Bean
    public HtmlParsingService htmlParsingService(){
        return new HtmlParsingServiceImpl();
    }
    @Bean
    public CrawlerService crawlerService(){
        return new CrawlerServiceImpl();
    }

    @Bean
    public App app(){
        return new App();
    }
}
