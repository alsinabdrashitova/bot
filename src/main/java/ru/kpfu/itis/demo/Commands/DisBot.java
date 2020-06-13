package ru.kpfu.itis.demo.Commands;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import ru.kpfu.itis.demo.TestComp;
import ru.kpfu.itis.demo.repository.WordRepository;
import ru.kpfu.itis.demo.service.WordService;

import javax.security.auth.login.LoginException;

public class DisBot {

    public static void start(WordService wordService, TestComp testComp) throws LoginException, RateLimitedException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "token";
        builder.setToken(token);
        CommandLead commandLead = new CommandLead(wordService, testComp);
        testComp.commandLead = commandLead;
        builder.addEventListener(commandLead);
        builder.buildAsync();
    }
}
