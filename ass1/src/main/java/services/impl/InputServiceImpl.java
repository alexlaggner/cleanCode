package services.impl;

import models.dto.CrawlerInputInformation;
import models.enumerations.Language;
import org.springframework.stereotype.Service;
import services.interfaces.InputService;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    private void initializeServiceObjects(){
        this.scanner = new Scanner(System.in);
    }

}
