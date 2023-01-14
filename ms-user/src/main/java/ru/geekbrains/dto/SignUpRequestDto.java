package ru.geekbrains.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String email;
    private String password;
}
