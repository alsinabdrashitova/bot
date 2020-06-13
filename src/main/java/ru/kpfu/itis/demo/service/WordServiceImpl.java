package ru.kpfu.itis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.demo.dto.WordDto;
import ru.kpfu.itis.demo.model.Word;
import ru.kpfu.itis.demo.repository.WordRepository;

import java.util.List;

@Component
public class WordServiceImpl implements WordService {

    @Autowired
    private WordRepository wordRepository;


    @Override
    public WordDto addWord(String word) {
       WordDto wordDto = new WordDto();
        List<Word> words = wordRepository.findAll();
        if (words.size() != 0) {
            Word word1 = words.get(0);
            String word2 = word1.getWord();
            if (word.startsWith(word2.substring(word2.length()-1, word2.length()))) {
                wordDto.setStatus(true);
                wordDto.setMessage("Продолжаем. Последнее слово: " + word);
                wordDto.setAllMessage("Продолжаем. Последнее слово: " + word);
                word1.setWord(word);
                wordRepository.save(word1);
            } else {
                wordDto.setStatus(false);
                wordDto.setMessage("Буквы не совпадают. Попробуй ещё раз. Последнее слово: " + word2);
            }
        } else {
            wordDto.setStatus(true);
            wordDto.setMessage("Последнее слово: " + word);
            wordDto.setAllMessage("Последнее слово: " + word);
            Word obj = Word.builder().id((long) 1).word(word).build();
            wordRepository.save(obj);
        }
        return wordDto;
    }
}
