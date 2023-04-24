package services.interfaces;

import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;

import java.util.List;

public interface CrawlerService {
    public CrawlerOutputInformation getCrawlerOutputInformation(CrawlerInputInformation inputInformation, String previousUrl);
    public List<CrawlerOutputInformation> crawlRecursively(CrawlerInputInformation inputInformation, String previousUrl);
    }
