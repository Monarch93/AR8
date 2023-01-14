package ru.geekbrains.order.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "cost_per_unit")
    private Double costPerProduct;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "quantity")
    private int quantity;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(CartItem cartItem) {
        this.productId = cartItem.getProductId();
        this.quantity = cartItem.getQuantity();
        this.costPerProduct = cartItem.getCostPerProduct();
        this.cost = cartItem.getCost();
    }
}
