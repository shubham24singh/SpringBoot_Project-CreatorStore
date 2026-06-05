package in.ujjwalsingh.creatorstor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)

    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    private String catagory;

    //@NotBlank(message = "price is required")
    @NotNull(message = "price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0.0")
    @Column(nullable = false)
    private BigDecimal price;

   // @NotBlank(message = "stock quantity is required")
    @NotNull(message = "stock quantity is required")
    @Min(value = 0, message = "stock can not be less than 0")
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
}










