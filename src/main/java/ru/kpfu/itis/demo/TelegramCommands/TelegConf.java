package ru.kpfu.itis.demo.TelegramCommands;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ru.kpfu.itis.demo.TelegramCommands")
@PropertySource("classpath:telegram.properties")
public class TelegConf {
}
