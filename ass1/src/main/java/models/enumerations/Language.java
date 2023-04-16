package models.enumerations;

public enum Language {

    DE("Deutsch", "German",0), EN ("Englisch","English",1);

    String nameDe;
    String nameEn;
    int langId;

    private Language(String nameDe, String nameEn, int langId){
        this.nameDe = nameDe;
        this.nameEn = nameEn;
        this.langId = langId;
    }

    public static Language getByLangId(int langId){
        for (Language lang : Language.values()) {
            if(lang.getLangId() == langId){
                return lang;
            }
        }
        return null;
    }

    public String getNameDe() {
        return nameDe;
    }

    public String getNameEn() {
        return nameEn;
    }

    public int getLangId() {
        return langId;
    }
}
