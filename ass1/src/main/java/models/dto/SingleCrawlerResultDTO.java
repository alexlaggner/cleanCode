package models.dto;

import java.util.LinkedList;
import java.util.List;

public class SingleCrawlerResultDTO {
    private CrawlerInputInformation inputInformation;
    private List<CrawlerOutputInformation> outputInformation = new LinkedList<>();

    public CrawlerInputInformation getInputInformation() {
        return inputInformation;
    }

    public void setInputInformation(CrawlerInputInformation inputInformation) {
        this.inputInformation = inputInformation;
    }

    public List<CrawlerOutputInformation> getOutputInformation() {
        return outputInformation;
    }

    public void setOutputInformation(List<CrawlerOutputInformation> outputInformation) {
        this.outputInformation = outputInformation;
    }
}
