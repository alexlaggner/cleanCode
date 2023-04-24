package services.interfaces;

import models.enumerations.Language;
import org.apache.http.client.methods.HttpPost;

import java.util.List;

public interface TranslationService {
    public List<String> translateHeadings(List<String> headings, Language language);
}
