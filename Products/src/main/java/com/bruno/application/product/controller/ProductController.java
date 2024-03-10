package com.bruno.application.product.controller;

import com.bruno.application.product.dto.ProductDTO;
import com.bruno.application.product.entity.Product;
import com.bruno.application.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Get list of product")
    public List<Product> getExamples() {
        return productService.getAll();
    }

    @PostMapping
    @Operation(summary = "Create a new product test")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create Product",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Validation failed for object=",
                    content = @Content)})
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody
                                                        final ProductDTO product) {
        try {
            final ProductDTO savedProduct = productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(product);
        }
    }
}
