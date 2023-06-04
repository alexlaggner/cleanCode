package services.interfaces;

import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;

import java.io.IOException;
import java.util.List;

public interface MarkDownExportService {
    public void exportCrawlerOutputInformation(CrawlerInputInformation inputInformation, List<CrawlerOutputInformation> outputInformation) throws IOException;
    public void exportMultipleCrawlerOutputinformation(List<CrawlerInputInformation> inputInformation, List<CrawlerOutputInformation> outputInformation) throws IOException;
}
