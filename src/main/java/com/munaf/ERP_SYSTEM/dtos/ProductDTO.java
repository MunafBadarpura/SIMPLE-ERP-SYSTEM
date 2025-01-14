package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.Product;
import com.munaf.ERP_SYSTEM.entities.enums.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO {

    private Long id;

    private String productName;

    private Category category;

    private Long productStock;

    private Long productPrice;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public Product productDtoToProduct() {
        Product product = new Product();
        product.setProductName(this.getProductName());
        product.setCategory(this.getCategory());
        product.setProductStock(this.getProductStock());
        product.setProductPrice(this.getProductPrice());
        return product;
    }

    public static ProductDTO productToProductDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setCategory(product.getCategory());
        productDTO.setProductStock(product.getProductStock());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setUpdatedAt(product.getUpdatedAt());

        return productDTO;
    }

}
