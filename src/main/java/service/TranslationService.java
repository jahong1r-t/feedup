package service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TranslationService {
    private static final List<String> SUPPORTED_LANGUAGES = Arrays.asList("uz", "en", "ru");
    private static final Map<String, Map<String, String>> translations = new HashMap<>();

    static {
        for (String lang : SUPPORTED_LANGUAGES) {
            loadTranslations(lang);
        }
    }

    private static void loadTranslations(String lang) {
        String filename = "message_" + lang + ".properties";

        try (InputStream inputStream = TranslationService.class.getClassLoader().getResourceAsStream(filename)) {
            if (inputStream == null) {
                System.err.println("❌ Translation file not found: " + filename);
                return;
            }

            Properties prop = new Properties();

            prop.load(inputStream);

            Map<String, String> map = new HashMap<>();

            for (String key : prop.stringPropertyNames()) {
                map.put(key, prop.getProperty(key));
            }

            translations.put(lang, map);
            System.out.println("Loaded translations for: " + lang);

        } catch (IOException e) {
            System.err.println("Failed to load " + filename + ": " + e.getMessage());
        }
    }

    public static String get(String lang, String key) {
        Map<String, String> languages = translations.get(lang);
        if (languages == null) {
            languages = translations.get("uz");
        }
        return languages.getOrDefault(key, "Not Found");
    }
}
