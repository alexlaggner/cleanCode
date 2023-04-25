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
import org.jsoup.select.Elements;
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
        Elements links = jsoupDocument.select("a");
        Elements headers = jsoupDocument.select("h1, h2, h3, h4, h5, h6");
        if(links != null) {
            for (Element link : links) {
                data.getLinks().add(link.attr("href"));
            }
        }
        if(headers != null) {
            for (Element header : headers) {
                data.getHeaders().add(header.text());
            }
        }
        return data;
    }
    @PostConstruct
    private void initializeServiceObjects(){
        this.httpClient = HttpClients.createDefault();
    }
}
