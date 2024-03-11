package com.bruno.application.product.mapper;

import com.bruno.application.product.dto.ProductRQ;
import com.bruno.application.product.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final ModelMapper modelMapper;

    public ProductMapper() {
        this.modelMapper = new ModelMapper();
    }

    public Product convertToEntity(ProductRQ productRQ) {
        return modelMapper.map(productRQ, Product.class);
    }

    public ProductRQ convertToDTO(Product product) {
        return modelMapper.map(product, ProductRQ.class);
    }
}
