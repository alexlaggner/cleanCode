import java.util.Scanner;

public class Main {
    /*
    input the URL, the depth of websites to crawl, and the target language to the command line
    create a compact overview of the crawled websites in a specified target language (e.g. translate it into German)
        record and translate only the headings
        represent the depth of the crawled websites with proper indentation (see example)
        record the URLs of the crawled sites
        highlight broken links
    find the links to other websites and recursively do the analysis for those websites (it is enough if you analyze the pages at a depth of 2 without visiting further links, you might also allow the user to configure this depth via the command line)
    store the results in a single markdown file (.md extension
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Webcrawler!\n\n");
        //Input: Depht, target lang, url
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie die gewünschte URL an:\n");
        String url = scanner.next();
        System.out.println("Bitte geben Sie nun die gewünschte Tiefe an:\n");
        int depth = scanner.nextInt();
        System.out.println("Bitte geben Sie nun die gewünschte Sprache ein.\n0 - Deutsch\n1 - Englisch\n");
        int langId = scanner.nextInt();

        //Output: headings (translated)

    }
}
