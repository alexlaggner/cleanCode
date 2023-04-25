
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import models.enumerations.Language;
import services.impl.TranslationServiceImpl;
import services.interfaces.TranslationService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TranslationServiceImpl.class)
public class TranslationServiceTests {

    //Mocked Entities
    @MockBean
    private HttpClient httpClient;
    @MockBean
    private List<HttpResponse> responses;

    private HttpResponse firstHttpResponse = Mockito.mock(HttpResponse.class);
    private HttpResponse secondHttpResponse = Mockito.mock(HttpResponse.class);
    private HttpResponse thirdHttpResponse = Mockito.mock(HttpResponse.class);
    //Class to test
    @Autowired
    private TranslationService translationService;

    //variables for setup
    private List<HttpPost> httpPosts = new LinkedList<>();
    private List<String> responseString = new LinkedList<>();

    private List<String> headings;
    private Language language;

    //Information on API Endpoint
    private static final String API_ENDPOINT = "google-translator9.p.rapidapi.com";
    private static final String API_KEY="f5d9689c49msh52e52af4cc1a8c1p1125dbjsn7963f5a59953";
    private static final String URL ="https://google-translator9.p.rapidapi.com/v2";


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        String [] headings = {"Header1", "Header2", "Header3"};
        List<String> headingList = Arrays.asList(headings);
        this.headings = headingList;
        this.language = Language.EN;


        for (String text : headingList) {
            HttpPost httpPost = new HttpPost(URL);
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("X-RapidAPI-Key", API_KEY);
            httpPost.setHeader("X-RapidAPI-Host", API_ENDPOINT);

            StringEntity entity = new StringEntity("{\"q\":\"" + text + "\",\"target\":\"" + language.getCode() + "\",\"format\":\"text\"}",
                    ContentType.APPLICATION_JSON);

            httpPost.setEntity(entity);
            this.httpPosts.add(httpPost);
            String firstResponseString ="{\n" +
                    "  \"data\": {\n" +
                    "    \"translations\": [\n" +
                    "      {\n" +
                    "        \"translatedText\": \"Header1\",\n" +
                    "        \"detectedSourceLanguage\": \"de\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}";
            String secondResponseString ="{\n" +
                    "  \"data\": {\n" +
                    "    \"translations\": [\n" +
                    "      {\n" +
                    "        \"translatedText\": \"Header2\",\n" +
                    "        \"detectedSourceLanguage\": \"de\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}";
            String thirdResponseString ="{\n" +
                    "  \"data\": {\n" +
                    "    \"translations\": [\n" +
                    "      {\n" +
                    "        \"translatedText\": \"Header3\",\n" +
                    "        \"detectedSourceLanguage\": \"de\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}";
            this.responseString.add(firstResponseString);
            this.responseString.add(secondResponseString);
            this.responseString.add(thirdResponseString);

        }
    }

    @Test
    public void testTranslation() throws Exception {
        Mockito.when(httpClient.execute(httpPosts.get(0))).thenReturn((firstHttpResponse));
        Mockito.when(firstHttpResponse.getEntity()).thenReturn(new StringEntity(responseString.get(0)));

        Mockito.when(httpClient.execute(httpPosts.get(1))).thenReturn((secondHttpResponse));
        Mockito.when(secondHttpResponse.getEntity()).thenReturn(new StringEntity(responseString.get(1)));

        Mockito.when(httpClient.execute(httpPosts.get(2))).thenReturn((thirdHttpResponse));
        Mockito.when(thirdHttpResponse.getEntity()).thenReturn(new StringEntity(responseString.get(2)));

        translationService.translateHeadings(headings,language);
        Assert.assertEquals("Header1", headings.get(0));
        Assert.assertEquals("Header2", headings.get(1));
        Assert.assertEquals("Header3", headings.get(2));

    }
}
