package ru.geekbrains.routing.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.entity.ProductDto;

import java.util.List;

@FeignClient("ms-product")
public interface ProductClient {
    @GetMapping("/api/v1/products/{id}")
    ProductDto findProductById(@PathVariable Long id);

    @GetMapping("/api/v1/products/ids")
    List<ProductDto> findProductsByIds(@RequestParam List<Long> ids);
}
