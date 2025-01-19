package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.services.ProductService;
import com.munaf.ERP_SYSTEM.utils.PageResponseModel;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/{userId}/product")
@Tag(name = "PRODUCT APIs", description = "With the help of this APIs user can get see their products and track of stock, price etc")
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
