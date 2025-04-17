package util;

import static util.Button.*;

public interface Keyboard {
    /// ALL languages
    String[][] languages = {
            {UZ, EN, RU},
    };

    /// ///User panel keyboard
    String[][] main_uz = {
            {MENU_UZ},
            {MY_ORDERS_UZ},
            {BASKET_UZ, CALL_UZ},
            {SEND_MESSAGE_UZ, SETTINGS_UZ},
            {ABOUT_UZ}
    };

    String[][] main_en = {
            {MENU_EN},
            {MY_ORDERS_EN},
            {BASKET_EN, CALL_EN},
            {SEND_MESSAGE_EN, SETTINGS_EN},
            {ABOUT_EN}
    };

    String[][] main_ru = {
            {MENU_RU},
            {MY_ORDERS_RU},
            {BASKET_RU, CALL_RU},
            {SEND_MESSAGE_RU, SETTINGS_RU},
            {ABOUT_RU}
    };
}
