package ru.geekbrains.order.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.geekbrains.entity.ProductDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "cost_per_product")
    private Double costPerProduct;

    @Column(name = "cost")
    private Double cost;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public CartItem(ProductDto product) {
        this.productId = product.getId();
        this.quantity = 1;
        this.costPerProduct = product.getCost();
        this.cost = this.costPerProduct;
    }

    public void incrementQuantity() {
        quantity++;
        cost = quantity * costPerProduct;
    }

    public void incrementQuantity(int amount) {
        quantity += amount;
        cost = quantity * costPerProduct;
    }
}