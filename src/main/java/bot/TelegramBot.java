package bot;

import entity.User;
import entity.enums.Role;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import repository.UserRepository;
import service.AdminService;
import service.AuthService;
import service.UserService;
import util.BotConfig;

import static db.DataSource.*;

public class TelegramBot extends TelegramLongPollingBot {
    private final AuthService authService = new AuthService();
    private UserService userService;
    private final UserRepository userRepository = new UserRepository();
    private final BotConfig botConfig = new BotConfig();


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Long chatId = update.getMessage().getChatId();
            User user = userSession.get(chatId);

            if (user == null) {
                user = userRepository.getUser(chatId).isPresent() ? userRepository.getUser(chatId).get() : null;
                if (user != null) {
                    userSession.put(chatId, user);
                }
            }

            if (user == null || !user.getIsRegister()) {
                authService.service(update);
            } else {
                if (user.getRole().equals(Role.ADMIN)) {
                    new AdminService().service(update);
                } else {
                    userService.service(update);
                }
            }
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
