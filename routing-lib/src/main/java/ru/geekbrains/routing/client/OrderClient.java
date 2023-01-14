package ru.geekbrains.routing.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("ms-order")
public interface OrderClient {
}
