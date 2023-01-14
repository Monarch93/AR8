package ru.geekbrains.routing.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.geekbrains.dto.AuthRequestDto;
import ru.geekbrains.dto.SignUpRequestDto;
import ru.geekbrains.dto.AuthResponseDTO;

@FeignClient("ms-user")
public interface UserClient {

    @PostMapping("/signup")
    String signUp(@RequestBody SignUpRequestDto signUpRequest);

    @PostMapping("/login")
    AuthResponseDTO login(@RequestBody AuthRequestDto request);
}
