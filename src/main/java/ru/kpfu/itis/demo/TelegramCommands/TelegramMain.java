package ru.kpfu.itis.demo.TelegramCommands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import ru.kpfu.itis.demo.TelegramCommands.Bot;
import ru.kpfu.itis.demo.TestComp;
import ru.kpfu.itis.demo.service.WordService;

@Component
public class TelegramMain implements CommandLineRunner {

    @Autowired
    private TestComp testComp;

    @Autowired
    private WordService wordService;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TelegramMain.class);
        application.run(args);

        ApiContextInitializer.init();
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//        try{
//            telegramBotsApi.registerBot(new Bot());
//        }catch (TelegramApiRequestException e){
//            e.printStackTrace();
        }
        static {
            System.getProperties().put("proxySet", "true");
            System.getProperties().put("socksProxyHost", "127.0.0.1");
            System.getProperties().put("socksProxyPort", "9150");
            ApiContextInitializer.init();
        }

    @Override
    public void run(String... args) throws Exception {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            Bot bot = new Bot(wordService, testComp);
            telegramBotsApi.registerBot(bot);
            testComp.bot = bot;
        }catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
    }
}
