package ru.geekbrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.geekbrains")
public class MsUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsUserApplication.class, args);
    }
}
