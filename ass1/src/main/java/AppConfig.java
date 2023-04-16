import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import services.InputService;
import services.InputServiceImpl;

@Configuration
public class AppConfig {
    @Bean
    public InputService inputService() {
        return new InputServiceImpl();
    }

    @Bean
    public App app(){
        return new App();
    }
}
