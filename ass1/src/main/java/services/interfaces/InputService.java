package services.interfaces;

import models.dto.CrawlerInputInformation;
import org.springframework.stereotype.Service;

@Service
public interface InputService {
    /**
     * Takes user input to return an instance of a CrawlerInputInformation
     * @return
     */
    public CrawlerInputInformation getCrawlerInputInformation();
}
