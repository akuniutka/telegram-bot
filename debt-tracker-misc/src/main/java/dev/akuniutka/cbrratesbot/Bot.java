package dev.akuniutka.cbrratesbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// TODO: find why Telegram API (not Scheduled Service!) delays exit
// TODO: find versions of Swagger and Spring Boot, which break compatibility, and the source of error
// TODO: move from Swagger to SpringDoc
// TODO: move to Spring Boot 2.7.11
// TODO: refactor the code according to Clean Code

@EnableScheduling
@SpringBootApplication
public class Bot {
    public static void main(String[] args) {
        SpringApplication.run(Bot.class, args);
    }
}
