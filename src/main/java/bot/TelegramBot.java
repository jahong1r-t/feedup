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
import util.Bot;

import java.util.HashMap;
import java.util.Map;

public class TelegramBot extends TelegramLongPollingBot {
    private final AuthService authService = new AuthService();
    private final UserService userService = new UserService();
    private final AdminService adminService = new AdminService();
    private final UserRepository userRepository = new UserRepository();

    private final Map<Long, User> userSession = new HashMap<>();

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Long chatId = update.getMessage().getChatId();
            User user = userSession.get(chatId);

            if (user == null) {
                user = userRepository.getUser(chatId);
                if (user != null) {
                    userSession.put(chatId, user);
                }
            }

            if (user == null || !user.getIsRegister()) {
                authService.service();
            } else {
                if (user.getRole().equals(Role.ADMIN)) {
                    adminService.service(update);
                } else {
                    userService.service(update);
                }
            }
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
