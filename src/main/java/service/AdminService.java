package service;

import bot.TelegramBot;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import util.Utils;

public class AdminService extends TelegramBot {

    @SneakyThrows
    public void service(Update update) {
        switch (update.getMessage().getText()) {
            case "/start" -> {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Hi admin");
                execute(sendMessage);
            }
        }
    }
}
