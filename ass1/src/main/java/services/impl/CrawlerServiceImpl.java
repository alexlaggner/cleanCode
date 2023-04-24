package services.impl;

import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;
import models.dto.HtmlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.interfaces.CrawlerService;
import services.interfaces.HtmlDataService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class CrawlerServiceImpl implements CrawlerService {

    @Autowired
    private HtmlDataService htmlDataService;

    public CrawlerOutputInformation getCrawlerOutputInformation(CrawlerInputInformation inputInformation, String previousUrl){
        CrawlerOutputInformation outputInformation = new CrawlerOutputInformation();
        String htmlString = null;
        try {
            htmlString = htmlDataService.getHtmlStringFromCrawlerInputInformation(inputInformation);
            HtmlData htmlData = htmlDataService.extractDataFromHtmlString(htmlString);

            outputInformation.setHeaders(htmlData.getHeaders());
            outputInformation.setLinks(htmlData.getLinks());

        } catch (IOException e) {
            outputInformation.setBrokenLink(true);
        }
        catch (IllegalArgumentException e){
            outputInformation.setBrokenLink(true);
        }
        catch (Exception e){
            outputInformation.setBrokenLink(true);
        }

        outputInformation.setUrl(inputInformation.getUrl());
        outputInformation.setDepth(inputInformation.getDepth());
        outputInformation.setPreviousUrl(previousUrl);

        return outputInformation;
    }

    public List<CrawlerOutputInformation> crawlRecursively(CrawlerInputInformation inputInformation, String previousUrl){
        List<CrawlerOutputInformation> result = new LinkedList<CrawlerOutputInformation>();
        CrawlerOutputInformation output = getCrawlerOutputInformation(inputInformation, previousUrl);
        result.add(output);

        if(inputInformation.getDepth() > 0 && !output.isBrokenLink()){
            for (String link : output.getLinks()) {
                CrawlerInputInformation recursiveInput = new CrawlerInputInformation();
                recursiveInput.setUrl(link);
                recursiveInput.setDepth(inputInformation.getDepth()-1);
                recursiveInput.setLanguage(inputInformation.getLanguage());

                result.addAll(crawlRecursively(recursiveInput,inputInformation.getUrl()));
            }
        }
        return result;
    }
}
