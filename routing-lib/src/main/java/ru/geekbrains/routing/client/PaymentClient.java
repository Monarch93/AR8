package ru.geekbrains.routing.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("ms-payment")
public interface PaymentClient {
}
