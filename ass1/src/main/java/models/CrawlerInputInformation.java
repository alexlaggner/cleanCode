package models;

import models.enumerations.Language;

import java.net.MalformedURLException;
import java.net.URL;

public class CrawlerInputInformation {
    private String url;
    private int depth;
    private Language language;

    public CrawlerInputInformation(){}

    public CrawlerInputInformation(String url, int depth, int langId) {
        this.url = url;
        this.depth = depth;
        this.language = Language.getByLangId(langId);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
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
