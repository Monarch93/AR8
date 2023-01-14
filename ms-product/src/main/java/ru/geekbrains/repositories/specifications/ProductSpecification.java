package ru.geekbrains.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.entity.Product;

public class ProductSpecification {
    private static Specification<Product> priceGreaterOrEqualsThan(int minCost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minCost);
    }

    private static Specification<Product> priceLesserOrEqualsThan(int maxCost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxCost);
    }

    private static Specification<Product> titleLike(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }

    public static Specification<Product> build(MultiValueMap<String, String> params) {
        Specification<Product> spec = Specification.where(null);
        if (params.containsKey("min_cost") && !params.getFirst("min_cost").isBlank()) {
            spec = spec.and(ProductSpecification.priceGreaterOrEqualsThan(Integer.parseInt(params.getFirst("min_price"))));
        }
        if (params.containsKey("max_cost") && !params.getFirst("max_cost").isBlank()) {
            spec = spec.and(ProductSpecification.priceLesserOrEqualsThan(Integer.parseInt(params.getFirst("max_price"))));
        }
        if (params.containsKey("title") && !params.getFirst("title").isBlank()) {
            spec = spec.and(ProductSpecification.titleLike(params.getFirst("title")));
        }
        return spec;
    }
}
