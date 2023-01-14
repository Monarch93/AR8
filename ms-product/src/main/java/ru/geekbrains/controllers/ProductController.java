package ru.geekbrains.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Services.ProductService;
import ru.geekbrains.entity.ProductDto;
import ru.geekbrains.repositories.specifications.ProductSpecification;

import java.util.List;

@Component
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }

        return productService.findAll(ProductSpecification.build(params), page, 4);
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productService.findProductDtoById(id).get();
    }

    @GetMapping("/ids")
    public List<ProductDto> findProductById(@RequestParam List<Long> ids) {
        return productService.findProductDtosByIds(ids);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveNewProduct(@RequestBody ProductDto product) {
        return productService.saveOrUpdate(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void updateProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
