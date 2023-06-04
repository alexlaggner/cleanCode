import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;
import models.enumerations.Language;
import services.interfaces.CrawlerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrawlerService.class)
public class CrawlerServiceCrawlRecursivelyTests {

    @MockBean
    private CrawlerService crawlerService;

    private CrawlerInputInformation inputInformation;
    private CrawlerOutputInformation outputInformation;
    private String previousUrl = "-";

    @Before
    public void setUp() {
        inputInformation = new CrawlerInputInformation();
        inputInformation.setUrl("https://www.google.com");
        inputInformation.setDepth(2);
        inputInformation.setLanguage(Language.EN);

        outputInformation = new CrawlerOutputInformation();
        outputInformation.setUrl(inputInformation.getUrl());
        outputInformation.setDepth(inputInformation.getDepth());
        outputInformation.setBrokenLink(true);
        outputInformation.setHeaders(Collections.<String>emptyList());
        outputInformation.setLinks(Collections.<String>emptyList());
    }
/*
    @Test
    public void testCrawlRecursivelyWithValidInput() throws Exception {
        when(crawlerService.getCrawlerOutputInformation(inputInformation, previousUrl)).thenReturn(outputInformation);
        List<CrawlerOutputInformation> actualResults = crawlerService.crawlRecursively(inputInformation, previousUrl);
        assertEquals(Collections.singletonList(outputInformation), actualResults);
    }*/

    @Test
    public void testCrawlRecursivelyWithInvalidInput() throws Exception {
        inputInformation.setDepth(-1);

        List<CrawlerOutputInformation> expectedResults = Collections.emptyList();

        List<CrawlerOutputInformation> actualResults = crawlerService.crawlRecursively(inputInformation, previousUrl);
        assertEquals(expectedResults, actualResults);
    }
}
