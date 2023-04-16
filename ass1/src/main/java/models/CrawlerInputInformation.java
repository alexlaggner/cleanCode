package models;

import models.enumerations.Language;

import java.net.MalformedURLException;
import java.net.URL;

public class CrawlerInputInformation {
    private URL url;
    private int depth;
    private Language language;

    public CrawlerInputInformation(){}

    public CrawlerInputInformation(String url, int depth, int langId) throws MalformedURLException {
        this.url = new URL(url);
        this.depth = depth;
        this.language = Language.getByLangId(langId);
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
