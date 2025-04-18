package entity.enums;

public enum Language {
    UZBEK("uz"),
    ENGLISH("en"),
    RUSSIAN("ru");

    private final String code;

    Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
