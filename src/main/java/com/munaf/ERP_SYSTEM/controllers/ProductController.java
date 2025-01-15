package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.services.ProductService;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/{userId}/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET ALL PRODUCTS
    @GetMapping
    public ResponseModel getAllProducts(@PathVariable Long userId) {
        return productService.getAllProducts(userId);
    }

    // GET PRODUCT BY ID
    @GetMapping("/{productId}")
    public ResponseModel getProductWithId(@PathVariable Long userId, @PathVariable Long productId) {
        return productService.getProductWithId(userId, productId);
    }
}
