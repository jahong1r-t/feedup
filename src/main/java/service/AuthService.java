package service;

import entity.enums.Language;
import entity.enums.State;
import org.telegram.telegrambots.meta.api.objects.Update;
import util.Utils;

import static db.DataSource.*;
import static entity.enums.State.*;
import static service.TranslationService.get;
import static util.Button.*;
import static util.Utils.*;
import static util.Keyboard.*;

public class AuthService {
    public void service(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        State current = states.getOrDefault(chatId, AUTHENTICATION);

        if (current == AUTHENTICATION) {
            switch (text) {
                case "/start" -> {
//                    util.sendMessage(chatId, "Assalomu alaykum", keyboard(languages));
                    states.put(chatId, AUTHENTICATION);
                }
                case UZ, EN, RU -> {
                    Language lang = text.equals(UZ) ? Language.UZBEK : text.equals(EN) ? Language.ENGLISH : Language.RUSSIAN;

//                    util.sendMessage(chatId, get(lang.getCode(), "auth.send_phone_number"), phoneNumberButton(get(lang.getCode(), "auth.phone.button.msg")));
                }
//                default -> util.sendMessage(chatId, "Error");
            }
        }
    }
}
