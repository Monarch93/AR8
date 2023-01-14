package ru.geekbrains.order.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.ProductDto;
import ru.geekbrains.order.dto.CartDto;
import ru.geekbrains.order.dto.OrderDto;
import ru.geekbrains.order.entity.Cart;
import ru.geekbrains.order.entity.Order;
import ru.geekbrains.order.repositories.OrderRepository;
import ru.geekbrains.routing.client.ProductClient;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;

    private final ProductClient productClient;

    private final ModelMapper modelMapper;

    private final OrderRepository orderRepository;

    @Transactional
    public OrderDto createFromUserCart(Long userId, UUID cartUuid, String address) {
        CartDto cartDto = cartService.findById(cartUuid);
        Cart cart = modelMapper.map(cartDto, Cart.class);
        Order order = new Order(cart, userId, address);
        order = orderRepository.save(order);
        return toDto(order);
    }

    public OrderDto findOrderById(Long id) {
        Order order = orderRepository.findById(id).get();
        List<Long> productIds = new ArrayList<>();
        order.getItems().forEach(item -> productIds.add(item.getProductId()));
        List<ProductDto> products = productClient.findProductsByIds(productIds);
        OrderDto orderDto = toDto(order);
        orderDto.setProducts(products);
        return orderDto;
    }

    public List<OrderDto> findAllOrdersByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId).stream().map(this::toDto).collect(Collectors.toList());
    }

    private OrderDto toDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }
}
