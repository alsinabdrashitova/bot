package ru.kpfu.itis.demo.dto;

import lombok.Data;

@Data
public class WordDto {
    public boolean status;
    private String message;
    private String allMessage;
}
