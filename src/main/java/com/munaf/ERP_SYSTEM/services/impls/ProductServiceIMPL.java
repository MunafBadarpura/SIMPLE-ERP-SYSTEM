package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.dtos.ProductDTO;
import com.munaf.ERP_SYSTEM.entities.Product;
import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.exceptions.ResourceNotFound;
import com.munaf.ERP_SYSTEM.repositories.MasterRepo;
import com.munaf.ERP_SYSTEM.services.ProductService;
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceIMPL implements ProductService {

    private final MasterRepo masterRepo;

    public ProductServiceIMPL(MasterRepo masterRepo) {
        this.masterRepo = masterRepo;
    }

    public void isUserExistWithId(Long userId) {
        Boolean isUserExists = masterRepo.getUserRepo().existsById(userId);
        if (!isUserExists) throw new ResourceNotFound("User not exist with Id : " + userId);
    }

    @Override
    public ResponseModel getAllProducts(Long userId) {
        isUserExistWithId(userId);
        List<Product> products = masterRepo.getProductRepo().findByUserId(userId);
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> ProductDTO.productToProductDto(product))
                .toList();

        return CommonResponse.OK(productDTOS);
    }

    @Override
    public ResponseModel getProductWithId(Long userId, Long productId) {
        isUserExistWithId(userId);
        Product product = masterRepo.getProductRepo().findByIdAndUserId(productId, userId)
                .orElseThrow(() -> new ResourceNotFound("Product Not Found With Id : " + productId));

        return CommonResponse.OK(ProductDTO.productToProductDto(product));
    }
}
