package ru.geekbrains.services;

import ru.geekbrains.models.UserInfo;

public interface ITokenService {
    String generateToken(UserInfo userInfo);
    UserInfo parseToken(String token);
}
