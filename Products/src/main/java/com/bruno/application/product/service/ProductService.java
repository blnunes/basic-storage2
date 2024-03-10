package com.bruno.application.product.service;

import com.bruno.application.product.dto.ProductDTO;
import com.bruno.application.product.entity.Product;
import com.bruno.application.product.mapper.ProductMapper;
import com.bruno.application.product.repository.ProtuctRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProtuctRepository repository;

    @Autowired
    private ProductMapper mapper;

    public List<Product> getAll() {
        log.info("Getting list of products");
        return repository.findAll();
    }

    public ProductDTO saveProduct(final ProductDTO productDTO) {
        Product productEntity = mapper.convertToEntity(productDTO);
        ProductDTO newProduct = mapper.convertToDTO(repository.save(productEntity));
        log.info("Saving the product {}", newProduct);
        return newProduct;
    }
}
