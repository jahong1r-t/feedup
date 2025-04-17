package bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.AuthService;
import util.Bot;

public class TelegramBot extends TelegramLongPollingBot {
    private final AuthService authService = new AuthService();

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            authService.service();
        }
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
