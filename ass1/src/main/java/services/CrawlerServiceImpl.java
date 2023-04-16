package services;

import models.CrawlerInputInformation;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;

@Service
public class CrawlerServiceImpl implements CrawlerService{

    private HttpClient httpClient;

    public String getHtmlDataFromCrawlerInputInformation(CrawlerInputInformation inputInformation) {
        try {
            URIBuilder uriBuilder = new URIBuilder(inputInformation.getUrl());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            //TODO:handle error case
        }
        return null;
    }

    @PostConstruct
    private void initializeServiceObjects(){
        this.httpClient = HttpClients.createDefault();
    }
}
