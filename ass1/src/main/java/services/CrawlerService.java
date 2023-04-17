package services;

import models.CrawlerInputInformation;
import models.CrawlerOutputInformation;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface CrawlerService {
    public CrawlerOutputInformation getCrawlerOutputInformation(CrawlerInputInformation inputInformation, String previousUrl);
    public List<CrawlerOutputInformation> crawlRecursively(CrawlerInputInformation inputInformation, String previousUrl);
    }
