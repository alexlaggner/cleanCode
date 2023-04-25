import models.dto.HtmlData;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import services.impl.HtmlDataServiceImpl;
import services.interfaces.HtmlDataService;

import java.util.LinkedList;

public class HtmlDataServiceTests {

    private HtmlDataService htmlDataService = new HtmlDataServiceImpl();

    private static final String htmlStringFirstPart = "<!DOCTYPE html><head><title>TEST</title></head><body>";
    private static final String htmlLinks = "<a href='#'>LINK1</a><br><a href='www.cleancode.at'><br><a href='https://www.noch-cleaner-coden.at'>LINK3</a>";
    private static final String htmlHeaders ="<h1>Header1</h1><h2>Header2</h2><h3>Header3</h3><h4>Header4</h4><h5>Header5</h5><h6>Header6</h6>";
    private static final String htmlStringLastPart = "</body></html>";


    @Test
    public void testExtractDataFromHtmlString() {
    String htmlString = htmlStringFirstPart + htmlLinks + htmlHeaders + htmlStringLastPart;
        HtmlData htmlData = htmlDataService.extractDataFromHtmlString(htmlString);

        Assert.assertEquals(6, htmlData.getHeaders().size());
        Assert.assertEquals("Header1",htmlData.getHeaders().get(0));
        Assert.assertEquals("Header2",htmlData.getHeaders().get(1));
        Assert.assertEquals("Header3",htmlData.getHeaders().get(2));
        Assert.assertEquals("Header4",htmlData.getHeaders().get(3));
        Assert.assertEquals("Header5",htmlData.getHeaders().get(4));
        Assert.assertEquals("Header6",htmlData.getHeaders().get(5));

        Assert.assertEquals(3, htmlData.getLinks().size());
        Assert.assertEquals("#",htmlData.getLinks().get(0));
        Assert.assertEquals("www.cleancode.at",htmlData.getLinks().get(1));
        Assert.assertEquals("https://www.noch-cleaner-coden.at", htmlData.getLinks().get(2));
    }

    @Test
    public void testExtractDataFromHtmlStringWithoutLinks(){
        String htmlString = htmlStringFirstPart + htmlHeaders + htmlStringLastPart;
        HtmlData htmlData = htmlDataService.extractDataFromHtmlString(htmlString);

        Assert.assertEquals(6, htmlData.getHeaders().size());
        Assert.assertEquals("Header1",htmlData.getHeaders().get(0));
        Assert.assertEquals("Header2",htmlData.getHeaders().get(1));
        Assert.assertEquals("Header3",htmlData.getHeaders().get(2));
        Assert.assertEquals("Header4",htmlData.getHeaders().get(3));
        Assert.assertEquals("Header5",htmlData.getHeaders().get(4));
        Assert.assertEquals("Header6",htmlData.getHeaders().get(5));

        Assert.assertEquals(new LinkedList<String>(), htmlData.getLinks());
    }
    @Test
    public void testExtractDataFromHtmlStringWithoutHeaders(){
        String htmlString = htmlStringFirstPart + htmlLinks + htmlStringLastPart;
        HtmlData htmlData = htmlDataService.extractDataFromHtmlString(htmlString);

        Assert.assertEquals(new LinkedList<String>(), htmlData.getHeaders());

        Assert.assertEquals(3, htmlData.getLinks().size());
        Assert.assertEquals("#",htmlData.getLinks().get(0));
        Assert.assertEquals("www.cleancode.at",htmlData.getLinks().get(1));
        Assert.assertEquals("https://www.noch-cleaner-coden.at", htmlData.getLinks().get(2));

    }
    @Test
    public void testExtractDataFromHtmlStringWithoutLinksAndHeaders(){
        String htmlString = htmlStringFirstPart + htmlStringLastPart;
        HtmlData htmlData = htmlDataService.extractDataFromHtmlString(htmlString);

        Assert.assertEquals(new LinkedList<String>(), htmlData.getHeaders());
        Assert.assertEquals(new LinkedList<String>(), htmlData.getLinks());
    }

    @Test
    public void testExtractDataFromNonHtmlString(){
        String noHtmlString ="Hallo! Ich bin HTML!";
        HtmlData htmlData = htmlDataService.extractDataFromHtmlString(noHtmlString);
        Assert.assertEquals(new LinkedList<String>(), htmlData.getHeaders());
        Assert.assertEquals(new LinkedList<String>(), htmlData.getLinks());
    }
}
