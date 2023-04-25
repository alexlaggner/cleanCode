import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;
import models.dto.HtmlData;
import models.enumerations.Language;
import services.impl.CrawlerServiceImpl;
import services.interfaces.CrawlerService;
import services.interfaces.HtmlDataService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrawlerServiceImpl.class)
public class CrawlerServiceGetOutputInformationTests {

    @MockBean
    private HtmlDataService htmlDataService;

    @Autowired
    private CrawlerService crawlerService;

    private CrawlerInputInformation inputInformation;

    private String htmlString;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        inputInformation = new CrawlerInputInformation();
        inputInformation.setUrl("https://www.example.com");
        inputInformation.setDepth(1);
        inputInformation.setLanguage(Language.EN);
        htmlString = "<html><body><h1>Hello World!</h1></body></html>";
    }

    @Test
    public void testGetCrawlerOutputInformationWithValidInput() throws Exception {
        Mockito.when(htmlDataService.getHtmlStringFromCrawlerInputInformation(inputInformation)).thenReturn(htmlString);
        Mockito.when(htmlDataService.extractDataFromHtmlString(htmlString)).thenReturn(new HtmlData());

        CrawlerOutputInformation outputInformation = crawlerService.getCrawlerOutputInformation(inputInformation, null);

        assertNotNull(outputInformation);
        assertFalse(outputInformation.isBrokenLink());
        assertTrue(outputInformation.getHeaders().isEmpty());
        assertTrue(outputInformation.getLinks().isEmpty());
        assertEquals(inputInformation.getUrl(), outputInformation.getUrl());
        assertEquals(inputInformation.getDepth(), outputInformation.getDepth());
    }

    @Test
    public void testGetCrawlerOutputInformationWithInvalidInput() throws Exception {
        Mockito.when(htmlDataService.getHtmlStringFromCrawlerInputInformation(inputInformation)).thenThrow(new IllegalArgumentException());

        CrawlerOutputInformation outputInformation = crawlerService.getCrawlerOutputInformation(inputInformation, null);

        assertNotNull(outputInformation);
        assertTrue(outputInformation.isBrokenLink());
        assertNull(outputInformation.getHeaders());
        assertNull(outputInformation.getLinks());
        assertEquals(inputInformation.getUrl(), outputInformation.getUrl());
        assertEquals(inputInformation.getDepth(), outputInformation.getDepth());
    }
}
