package ru.kpfu.itis.demo.TelegramCommands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.kpfu.itis.demo.Commands.CommandLead;
import ru.kpfu.itis.demo.TestComp;
import ru.kpfu.itis.demo.dto.WordDto;
import ru.kpfu.itis.demo.service.WordService;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public static List<MessageChannel> messageChannels = new ArrayList<>();

    public static List<Update> updates = new ArrayList<>();

    private WordService wordService;

    private TestComp testComp;


    public Bot(WordService wordService, TestComp testComp) {
        this.wordService = wordService;
        this.testComp = testComp;
    }


    @Override
    public void onUpdateReceived(Update update) {

        String start = "start";
        if (update.getMessage().getText().startsWith(start)) {
            if (!updates.contains(update)) {
                updates.add(update);
            }
            String msg = update.getMessage().getText().substring(start.length() + 1);
            WordDto wordDto = wordService.addWord(msg);
      //      event.getChannel().sendMessage(wordDto.getMessage()).queue();
            sendMsg(update.getMessage().getChatId().toString(), wordDto.getMessage());


            if (wordDto.status){
                testComp.commandLead.notifyAll(wordDto.getAllMessage());
            }
        }
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Exc");
        }
    }

    @Override
    public String getBotUsername() {
        return "name";
    }

    @Override
    public String getBotToken() {
        return "token";
    }

    public void notifyAll(String allMessage) {
        System.out.println("rfrmf");
        for (int i = 0; i < updates.size() ; i++) {
            sendMsg(updates.get(i).getMessage().getChatId().toString(), allMessage);
        }
    }
}