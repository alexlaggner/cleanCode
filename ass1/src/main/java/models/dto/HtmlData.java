package models.dto;

import java.util.LinkedList;
import java.util.List;

public class HtmlData {
    List<String> links = new LinkedList<String>();
    List<String> headers = new LinkedList<String>();

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
}
