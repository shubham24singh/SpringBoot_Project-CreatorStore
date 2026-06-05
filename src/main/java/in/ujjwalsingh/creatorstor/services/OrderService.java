package in.ujjwalsingh.creatorstor.services;

import in.ujjwalsingh.creatorstor.dto.OrderItemRequest;
import in.ujjwalsingh.creatorstor.dto.OrderRequest;
import in.ujjwalsingh.creatorstor.entities.Order;
import in.ujjwalsingh.creatorstor.entities.OrderItem;
import in.ujjwalsingh.creatorstor.entities.Product;
import in.ujjwalsingh.creatorstor.repositories.OrderRepository;
import in.ujjwalsingh.creatorstor.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order createOrder(OrderRequest orderRequest){
        List<OrderItem> orderItems= new ArrayList<>();
        BigDecimal totalPrice=BigDecimal.ZERO;
        Order order=new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setStatus("CONFIRMED");

        for(OrderItemRequest itemRequest: orderRequest.getItems()){
            //Product product= ProductRepository.findById(itemRequest.getProductId())
              //      .orElseThrow(()-> new RuntimeException("Product not found with Id"+ itemRequest.getProductId()));
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException(
                            "Product not found with Id " + itemRequest.getProductId()));
            // Check the Product Stock

            if(product.getStockQuantity()< itemRequest.getQuantity()){
                throw new RuntimeException("Not Enough Stock for "+itemRequest.getProductId());
            }

            // Calculate total Price
            BigDecimal priceOfItem= product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            totalPrice=totalPrice.add(priceOfItem);

           // Update the product Table with latest stock Quantity
            product.setStockQuantity(product.getStockQuantity()- itemRequest.getQuantity());

            productRepository.save(product);

            // Builder pattern to make Object

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .priceAtPurchase(product.getPrice())
                    .build();

            orderItems.add(orderItem);

        }
        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }

}
