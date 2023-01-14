package ru.geekbrains.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.AuthRequestDto;
import ru.geekbrains.dto.AuthResponseDTO;
import ru.geekbrains.dto.SignUpRequestDto;
import ru.geekbrains.entity.User;
import ru.geekbrains.models.UserInfo;
import ru.geekbrains.redis.repositories.RedisRepository;
import ru.geekbrains.repositories.JdbcRepository;
import ru.geekbrains.services.ITokenService;
import ru.geekbrains.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    private final ITokenService itokenService;

    private final RedisRepository redisRepository;

    private final JdbcRepository jdbcRepository;

    @GetMapping("/jdbc")
    public User registerUser(@RequestParam String email) {
        return jdbcRepository.getByEmail(email).get();
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody SignUpRequestDto signUpRequestDto) {
        User user = new User();
        user.setPassword(signUpRequestDto.getPassword());
        user.setEmail(signUpRequestDto.getEmail());
        userService.saveUser(user);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getEmail(), request.getPassword());
        List<String> roles = new ArrayList<>();
        user.getRole().forEach(role -> roles.add(role.getName()));
        UserInfo userInfo = UserInfo.builder()
                .userId(user.getId())
                .userEmail(user.getEmail())
                .role(roles)
                .build();
        String token = itokenService.generateToken(userInfo);
        return new AuthResponseDTO(token);
    }

    @GetMapping("/logout")
    public Boolean logout(@RequestHeader("Authorization") String token) {
        redisRepository.saveToken(token);
        return true;
    }
}
