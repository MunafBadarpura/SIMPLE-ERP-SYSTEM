package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.dtos.ProductDTO;
import com.munaf.ERP_SYSTEM.entities.Product;
import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.exceptions.ResourceNotFound;
import com.munaf.ERP_SYSTEM.repositories.MasterRepo;
import com.munaf.ERP_SYSTEM.services.ProductService;
import com.munaf.ERP_SYSTEM.utils.CommonPageResponse;
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.PageResponseModel;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "product")
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
    @Cacheable(key = "#userId + '_' + #getAllProducts")
    public PageResponseModel getAllProducts(Long userId, Integer pageNo, String sortBy) {
        isUserExistWithId(userId);


        Pageable pageable = PageRequest.of(pageNo-1, 10, Sort.by(sortBy));
        Page<Product> products = masterRepo.getProductRepo().findByUserId(userId, pageable);

        List<ProductDTO> productDTOS = products.getContent().stream()
                .map(product -> ProductDTO.productToProductDto(product))
                .toList();

        HashMap<String, Object> pageResult = new HashMap<>();
        pageResult.put("currentPage", pageNo);
        pageResult.put("totalPage", products.getTotalPages());
        pageResult.put("totalRecords", products.getTotalElements());

        return CommonPageResponse.OK(productDTOS,pageResult);
    }

    @Override
    @Cacheable(key = "#userId + '_' + #productId")
    public ResponseModel getProductWithId(Long userId, Long productId) {
        isUserExistWithId(userId);
        Product product = masterRepo.getProductRepo().findByIdAndUserId(productId, userId)
                .orElseThrow(() -> new ResourceNotFound("Product Not Found With Id : " + productId));

        return CommonResponse.OK(ProductDTO.productToProductDto(product));
    }
}
