package com.bruno.application.product.controller;

import com.bruno.application.product.dto.ProductRQ;
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
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Get list of product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Product",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductRQ.class))}),
            @ApiResponse(responseCode = "404", description = "Empty List",
                    content = @Content)})
    public ResponseEntity<List<ProductRQ>> getExamples() {
        return ResponseEntity.ok().body(
                Optional.ofNullable(productService.getAll())
                        .orElseThrow(() -> new NoSuchElementException("Empty list")));
    }

    @PostMapping
    @Operation(summary = "Create a new product test")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create Product",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductRQ.class))}),
            @ApiResponse(responseCode = "400", description = "Validation failed for object=",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request",
                    content = @Content)})
    public ResponseEntity<ProductRQ> createProduct(@Valid @RequestBody final ProductRQ product) {
        final ProductRQ savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
}
