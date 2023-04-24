package services.impl;

import models.enumerations.Language;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import services.interfaces.TranslationService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@Service
public class TranslationServiceImpl implements TranslationService {
    private static final String API_ENDPOINT = "google-translator9.p.rapidapi.com";
    private static final String API_KEY="f5d9689c49msh52e52af4cc1a8c1p1125dbjsn7963f5a59953";
    private static final String URL ="https://google-translator9.p.rapidapi.com/v2";
    private HttpClient httpClient;

    public List<String> translateHeadings(List<String> headings, Language language) {
        List<String> translatedList = new LinkedList<String>();
        try {
            if(headings != null){
            for (String heading : headings) {
                HttpPost httpPost = preparePostRequest(heading, language);

                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity responseEntity = response.getEntity();

                translatedList.add(EntityUtils.toString(responseEntity));
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return translatedList;
    }

    private HttpPost preparePostRequest(String text, Language language){
        HttpPost httpPost = new HttpPost(URL);
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("X-RapidAPI-Key", API_KEY);
        httpPost.setHeader("X-RapidAPI-Host", API_ENDPOINT);

        StringEntity entity = new StringEntity("{\"q\":\""+text+"\",\"target\":\""+language.getCode()+"\",\"format\":\"text\"}",
                ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);

        return httpPost;
    }

    @PostConstruct
    private void initializeServiceObjects() {
        this.httpClient = HttpClients.createDefault();
    }

}
