package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.Product;
import com.munaf.ERP_SYSTEM.entities.enums.Category;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ProductDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Product name cannot be blank.")
    @Size(min = 1, max = 20, message = "Product name must be between 1 and 20 characters.")
    private String productName;

    @Pattern(
            regexp = "ELECTRONICS|CLOTHING|SPORTS|HARDWARE|OTHER",
            message = "Category must be one of the following: ELECTRONICS, CLOTHING, SPORTS, HARDWARE, OTHER."
    )
    private String category;

    @NotNull(message = "Product stock cannot be null.")
    @Positive(message = "Product stock must be a positive number.")
    private Long productStock;

    @NotNull(message = "Product Purchase price cannot be null.")
    @Positive(message = "Product Purchase price must be a positive number.")
    private Long productPurchasePrice;

    @NotNull(message = "Product Purchase price cannot be null.")
    @Positive(message = "Product Purchase price must be a positive number.")
    private Long productSalePrice;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public Product productDtoToProduct() {
        Product product = new Product();
        product.setProductName(this.getProductName());
        product.setCategory(Category.valueOf(this.getCategory())); // product want enum and we send string
        product.setProductStock(this.getProductStock());
        product.setProductPurchasePrice(this.getProductPurchasePrice());
        product.setProductSalePrice(this.getProductSalePrice());
        return product;
    }

    public static ProductDTO productToProductDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setCategory(product.getCategory().name()); // productDTO want string and product share enum
        productDTO.setProductStock(product.getProductStock());
        productDTO.setProductPurchasePrice(product.getProductPurchasePrice());
        productDTO.setProductSalePrice(product.getProductSalePrice());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setUpdatedAt(product.getUpdatedAt());

        return productDTO;
    }

}
