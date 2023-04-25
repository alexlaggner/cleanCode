package services.interfaces;

import models.enumerations.Language;
import java.util.List;

public interface TranslationService {
    public List<String> translateHeadings(List<String> headings, Language language);
}
