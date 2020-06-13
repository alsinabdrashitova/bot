package ru.kpfu.itis.demo.Commands;

import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.demo.TestComp;
import ru.kpfu.itis.demo.service.WordService;

import javax.security.auth.login.LoginException;

@Component
public class Main implements CommandLineRunner {

    @Autowired
    WordService wordService;

    @Autowired
    TestComp testComp;

    public static void main(String[] args) throws LoginException, RateLimitedException {
//        JDABuilder builder = new JDABuilder(AccountType.BOT);
//        String token = "NzEwMDQ0MTU5OTcyNDc0OTEx.XruyrQ.8vezHBPWZ1KrKn3Pf0ePAiHbW6s";
//        builder.setToken(token);
//        builder.addEventListener(new CommandLead());
//        builder.buildAsync();
        SpringApplication application = new SpringApplication(Main.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        DisBot.start(wordService, testComp );
    }
}
