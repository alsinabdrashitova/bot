package ru.kpfu.itis.demo.Commands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import ru.kpfu.itis.demo.TelegramCommands.Bot;
import ru.kpfu.itis.demo.TelegramCommands.TelegConf;
import ru.kpfu.itis.demo.TelegramCommands.TelegramMain;
import ru.kpfu.itis.demo.TestComp;
import ru.kpfu.itis.demo.dto.WordDto;
import ru.kpfu.itis.demo.service.WordService;

import java.util.ArrayList;
import java.util.List;

public class CommandLead extends ListenerAdapter {

    public static List<MessageChannel> messageChannels = new ArrayList<>();

    public WordService wordService;

    public TestComp test;

    public CommandLead(WordService wordService, TestComp test) {
        this.wordService = wordService;
        this.test = test;
    }

    public void onMessageReceived(MessageReceivedEvent event) {


        String start = "start";
        if (!event.getAuthor().getName().equals("osena") && event.getMessage().getContent().startsWith(start)) {
            if (!messageChannels.contains(event.getChannel())) {
                messageChannels.add(event.getChannel());
            }
            String msg = event.getMessage().getContent().substring(start.length() + 1);
            WordDto wordDto = wordService.addWord(msg);
            event.getChannel().sendMessage(wordDto.getMessage()).queue();

            if(wordDto.status){
                System.out.println(test + "kfmvmg");
                test.bot.notifyAll(wordDto.getAllMessage());
            }
        }
    }

    public void notifyAll(String mess) {
        for (int i = 0; i < messageChannels.size(); i++) {
            messageChannels.get(i).sendMessage(mess).queue();
        }
    }
}
