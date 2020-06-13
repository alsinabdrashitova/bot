package ru.kpfu.itis.demo;

import org.springframework.stereotype.Component;
import ru.kpfu.itis.demo.Commands.CommandLead;
import ru.kpfu.itis.demo.TelegramCommands.Bot;
import ru.kpfu.itis.demo.TelegramCommands.TelegramMain;

@Component
public class TestComp {

    public Bot bot;
    public CommandLead commandLead;

}
