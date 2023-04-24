package models.dto;

import java.util.List;

public class CrawlerOutputInformation {
    private String url;
    private String previousUrl;
    private int depth;
    private List<String> headers;
    private List<String> links;
    private boolean brokenLink = false;

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public String getPreviousUrl() {
        return previousUrl;
    }

    public void setPreviousUrl(String previousUrl) {
        this.previousUrl = previousUrl;
    }

    public boolean isBrokenLink() {
        return brokenLink;
    }

    public void setBrokenLink(boolean brokenLink) {
        this.brokenLink = brokenLink;
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

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
}
