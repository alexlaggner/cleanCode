package services;

import models.CrawlerInputInformation;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.util.Scanner;

@Service
public class InputServiceImpl implements InputService{
    private Scanner scanner;

    public CrawlerInputInformation getCrawlerInputInformation()
    {
        System.out.println("Bitte geben Sie die gewünschte URL an:\n");
        String url = this.scanner.next();

        System.out.println("Bitte geben Sie nun die gewünschte Tiefe an:\n");
        int depth = this.scanner.nextInt();

        System.out.println("Bitte geben Sie nun die gewünschte Sprache ein.\n0 - Deutsch\n1 - Englisch\n");
        int langId = this.scanner.nextInt();

        return new CrawlerInputInformation(url, depth, langId);

    }

    @PostConstruct
    private void initializeServiceObjects(){
        this.scanner = new Scanner(System.in);
    }

}
