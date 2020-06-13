package ru.kpfu.itis.demo.service;

import org.springframework.stereotype.Component;
import ru.kpfu.itis.demo.dto.WordDto;

@Component
public interface WordService {
    public WordDto addWord(String word);
}
