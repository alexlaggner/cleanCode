package services;

import models.CrawlerInputInformation;
import org.springframework.stereotype.Service;

@Service
public interface CrawlerService {
    public String getHtmlDataFromCrawlerInputInformation(CrawlerInputInformation inputInformation);
}
