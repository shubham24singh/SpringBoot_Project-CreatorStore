package in.ujjwalsingh.creatorstor.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    @NotNull(message = "Product Id is Required")
    private Long productId;
    @NotNull(message = "Quantity is Required")
    @Min(value=1, message = "Quantity must be at least 1")
    private Integer quantity;

}
