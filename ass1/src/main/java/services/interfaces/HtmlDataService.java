package services.interfaces;

import models.dto.CrawlerInputInformation;
import models.dto.HtmlData;

import java.io.IOException;

public interface HtmlDataService {
    public String getHtmlStringFromCrawlerInputInformation(CrawlerInputInformation inputInformation) throws IOException;
    public HtmlData extractDataFromHtmlString(String htmlContent);
}
