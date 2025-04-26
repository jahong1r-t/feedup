package util;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
public class BotConfig {
    public String BOT_TOKEN;
    public String BOT_USERNAME;
    public String CLICK_API;

    public BotConfig() {
        try (FileInputStream input = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            this.BOT_TOKEN = properties.getProperty("bot.token");
            this.BOT_USERNAME = properties.getProperty("bot.username");
            this.CLICK_API = properties.getProperty("click.api-token");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
