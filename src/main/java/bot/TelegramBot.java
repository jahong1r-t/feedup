package bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import util.Bot;

public class TelegramBot extends TelegramLongPollingBot {

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
    }

    @Override
    public String getBotUsername() {
        return Bot.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return Bot.BOT_TOKEN;
    }
}
