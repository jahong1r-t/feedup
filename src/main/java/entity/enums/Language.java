package entity.enums;

import lombok.Getter;

@Getter
public enum Language {
    UZBEK("uz"),
    ENGLISH("en"),
    RUSSIAN("ru");

    private final String code;

    Language(String code) {
        this.code = code;
    }

}
