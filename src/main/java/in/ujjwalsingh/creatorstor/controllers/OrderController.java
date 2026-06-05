package in.ujjwalsingh.creatorstor.controllers;

import in.ujjwalsingh.creatorstor.dto.OrderRequest;
import in.ujjwalsingh.creatorstor.entities.Order;
import in.ujjwalsingh.creatorstor.repositories.OrderRepository;
import in.ujjwalsingh.creatorstor.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping
    public Order createOrder(@Valid @RequestBody OrderRequest orderRequest){
       return orderService.createOrder(orderRequest);
    }

    // Get All orders

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get Order By Id
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById( id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }




}
