package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.services.ProductService;
import com.munaf.ERP_SYSTEM.utils.PageResponseModel;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/{userId}/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET ALL PRODUCTS
    @GetMapping
    public PageResponseModel getAllProducts(@PathVariable Long userId,
                                            @RequestParam(defaultValue = "1") Integer pageNo,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        return productService.getAllProducts(userId,pageNo,sortBy);
    }

    // GET PRODUCT BY ID
    @GetMapping("/{productId}")
    public ResponseModel getProductWithId(@PathVariable Long userId, @PathVariable Long productId) {
        return productService.getProductWithId(userId, productId);
    }
}
