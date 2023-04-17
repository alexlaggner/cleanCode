package services;

import models.CrawlerInputInformation;
import models.HtmlData;

import java.io.IOException;

public interface HtmlDataService {
    public String getHtmlStringFromCrawlerInputInformation(CrawlerInputInformation inputInformation) throws IOException;
    public HtmlData extractDataFromHtmlString(String htmlContent);
}
