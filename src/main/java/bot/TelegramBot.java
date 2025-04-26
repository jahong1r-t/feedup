package bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import util.BotConfig;

public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig = new BotConfig();

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().getText().equals("/start")) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());
            execute(message);
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return botConfig.BOT_TOKEN;
    }
}
