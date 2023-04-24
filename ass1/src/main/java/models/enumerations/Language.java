package models.enumerations;

public enum Language {

    DE("Deutsch", "German",0,"de"),
    EN ("Englisch","English",1, "en"),
    DEFAULT("Standard (keine Übersetzung)", "standard (no translation)", -1,"");

    String nameDe;
    String nameEn;
    int langId;
    String code;

    private Language(String nameDe, String nameEn, int langId, String code){
        this.nameDe = nameDe;
        this.nameEn = nameEn;
        this.langId = langId;
        this.code = code;
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

    public String getCode() {
        return code;
    }
}
