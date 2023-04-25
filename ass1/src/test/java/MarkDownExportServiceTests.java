import models.dto.CrawlerInputInformation;
import models.dto.CrawlerOutputInformation;
import models.enumerations.Language;
import org.junit.Before;
import org.junit.Test;
import services.impl.MarkDownExportServiceImpl;
import services.interfaces.MarkDownExportService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MarkDownExportServiceTests {
    private MarkDownExportService markDownExportService = new MarkDownExportServiceImpl();
    private String expectedOutput;
    private CrawlerInputInformation inputInformation;
    private CrawlerOutputInformation firstOutputInformation;
    private CrawlerOutputInformation secondOutputInformation;

    @Before
    public void setUp() {
        this.expectedOutput = "INPUT:\n" +
                "URL: https://www.example.com\n" +
                "TIEFE:1\n" +
                "ZIELSPRACHE: Englisch\n" +
                "________________________________\n" +
                "URL: https://www.example.com/page1\n" +
                "TIEFE: 1\n" +
                "VORHERGEHENDER LINK: https://www.example.com\n" +
                "HEADERS:\n" +
                "Header1\n" +
                "Header2\n" +
                "________________________________\n" +
                "URL: https://www.example.com/page2\n" +
                "TIEFE: 1\n" +
                "VORHERGEHENDER LINK: https://www.example.com\n" +
                "HEADERS:\n" +
                "Header3\n" +
                "Header4\n" +
                "________________________________\n";

        this.inputInformation = new CrawlerInputInformation("https://www.example.com",
                1,
                Language.EN.getLangId());


        this.firstOutputInformation = new CrawlerOutputInformation();
        this.firstOutputInformation.setUrl("https://www.example.com/page1");
        this.firstOutputInformation.setDepth(1);
        this.firstOutputInformation.setPreviousUrl("https://www.example.com");
        this.firstOutputInformation.setHeaders(Arrays.asList("Header1", "Header2"));
        this.firstOutputInformation.setBrokenLink(false);

        this.secondOutputInformation = new CrawlerOutputInformation();
        this.secondOutputInformation.setUrl("https://www.example.com/page2");
        this.secondOutputInformation.setDepth(1);
        this.secondOutputInformation.setPreviousUrl("https://www.example.com");
        this.secondOutputInformation.setHeaders(Arrays.asList("Header3", "Header4"));
        this.secondOutputInformation.setBrokenLink(false);

    }

    @Test
    public void testMarkDownExportService() {
        List<CrawlerOutputInformation> outputInformation = Arrays.asList(
                this.firstOutputInformation,
                this.secondOutputInformation);

        try {
            markDownExportService.exportCrawlerOutputInformation(inputInformation, outputInformation);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        String actualOutput;
        try {
            actualOutput = new String(Files.readAllBytes(Paths.get("out.md")));
            //Aufgrund von verschiedener Formatierung werden sämtliche Whitespaces, Zeilenumbrüche usw entfernt
            this.expectedOutput  = this.expectedOutput.replaceAll("\\s+", "");
            actualOutput = actualOutput.replaceAll("\\s+", "");
            assertEquals(expectedOutput, actualOutput);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}
