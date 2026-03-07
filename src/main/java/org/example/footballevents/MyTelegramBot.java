package org.example.footballevents;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.logging.Logger;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {

    private final FootballApiService footballApiService;
    private static final Logger log = Logger.getLogger(MyTelegramBot.class.getName());
    private final String BOT_USERNAME = "AlexOpiBot"; // без @
    private final String BOT_TOKEN = "1352098341:AAG2oUtArDIBrnp5euPhRYy0Jz8i6PmclU4";

    public MyTelegramBot(@Lazy FootballApiService footballApiService) {
        this.footballApiService = footballApiService;
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/res")) {
                sendMessage(chatId, footballApiService.getAllMatches());
            }
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void registerBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
            log.info("TelegramBot успешно запущен через @PostConstruct...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
