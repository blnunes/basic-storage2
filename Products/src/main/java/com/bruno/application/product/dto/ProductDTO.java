package com.bruno.application.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotNull(message = "Name cannot be null")
    @Size(max = 20, min = 3, message = "The name must be (max 20 min 3)")
    private String name;
    @NotNull(message = "Name cannot be null")
    @Min(value = 1, message = "Quantity should not be less than 1")
    private Integer quantity;
}
