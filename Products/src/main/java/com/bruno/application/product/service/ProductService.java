package com.bruno.application.product.service;

import com.bruno.application.product.dto.ProductRQ;
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

    public List<ProductRQ> getAll() {
        log.info("Getting list of products");
        List<ProductRQ> productsRS = repository.findAll().stream().map(product -> mapper.convertToDTO(product))
                .toList();
        return productsRS.isEmpty() ? null : productsRS;
    }

    public ProductRQ saveProduct(final ProductRQ productRQ) {
        Product productEntity = mapper.convertToEntity(productRQ);
        ProductRQ newProduct = mapper.convertToDTO(repository.save(productEntity));
        log.info("Saving the product {}", newProduct);
        return newProduct;
    }

}
