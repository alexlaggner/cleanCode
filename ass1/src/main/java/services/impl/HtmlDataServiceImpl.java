package services.impl;

import models.dto.CrawlerInputInformation;
import models.dto.HtmlData;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import services.interfaces.HtmlDataService;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class HtmlDataServiceImpl implements HtmlDataService {

    private HttpClient httpClient;

    public String getHtmlStringFromCrawlerInputInformation(CrawlerInputInformation inputInformation) throws IOException {
        HttpGet getRequest = new HttpGet(inputInformation.getUrl());
        return EntityUtils.toString(this.httpClient.execute(getRequest).getEntity());
    }

    public HtmlData extractDataFromHtmlString(String htmlContent){
        Document jsoupDocument = Jsoup.parse(htmlContent);
        HtmlData data = new HtmlData();
        for (Element link : jsoupDocument.select("a")) {
            data.getLinks().add(link.attr("href"));
        }
        for (Element header : jsoupDocument.select("h1, h2, h3, h4, h5, h6")) {
            data.getHeaders().add(header.text());
        }

        return data;
    }
    @PostConstruct
    private void initializeServiceObjects(){
        this.httpClient = HttpClients.createDefault();
    }
}
