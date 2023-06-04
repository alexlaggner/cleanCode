package services.impl;

import models.dto.CrawlerInputInformation;
import models.enumerations.Language;
import org.springframework.stereotype.Service;
import services.interfaces.InputService;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Service
public class InputServiceImpl implements InputService {
    private Scanner scanner;

    public CrawlerInputInformation getCrawlerInputInformation()
    {
        System.out.println("Bitte geben Sie die gewünschte URL an:\n");
        String url = this.scanner.next();

        System.out.println("Bitte geben Sie nun die gewünschte Tiefe an:\n");
        int depth = this.scanner.nextInt();

        System.out.println("Bitte geben Sie nun die gewünschte Sprache ein.\n0 - Deutsch\n1 - Englisch\nWenn die Texte nicht übersetzt werden sollen, geben Sie irgendeinen Wert ein, der nicht in der obigen Liste ist.\n");
        int langId = this.scanner.nextInt();

        if(Language.getByLangId(langId) == null){
            langId = -1;
        }

        return new CrawlerInputInformation(url, depth, langId);
    }

    @Override
    public List<CrawlerInputInformation> getMultipleCrawlerInputInformation() {
        List<CrawlerInputInformation> inputInformation = new LinkedList<>();
        String proceed="";
        System.out.println("Bitte geben Sie die gewünschte Zielsprache ein.\n0 - Deutsch\n1 - Englisch\nWenn die Texte nicht übersetzt werden sollen, geben Sie irgendeinen Wert ein, der nicht in der obigen Liste ist.\n");
        int langId = this.scanner.nextInt();
        do{
            System.out.println("Bitte geben Sie die gewünschte URL an:\n");
            String url = this.scanner.next();

            System.out.println("Bitte geben Sie nun die gewünschte Tiefe an:\n");
            int depth = this.scanner.nextInt();

            inputInformation.add(new CrawlerInputInformation(url,depth,langId));
            System.out.println("Wollen Sie einen weiteren Crawlvorgang starten, geben sie bitte 'y' ein.\nWenn nicht, geben sie etwas Beliebiges anderes ein.");
            proceed = scanner.next();
        }while(proceed.equals("y"));
        return inputInformation;
    }

    @PostConstruct
    private void initializeServiceObjects(){
        this.scanner = new Scanner(System.in);
    }

}
