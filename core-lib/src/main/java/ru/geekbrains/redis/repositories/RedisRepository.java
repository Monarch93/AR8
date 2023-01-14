package ru.geekbrains.redis.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public void saveToken(String token) {
        redisTemplate.opsForValue().set("token:" + token, 1, Duration.ofHours(1));
    }

    public boolean checkToken(String token) {
        return redisTemplate.opsForValue().get("token:" + token) != null;
    }
}