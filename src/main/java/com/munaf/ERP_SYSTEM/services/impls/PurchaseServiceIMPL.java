package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.dtos.ProductDTO;
import com.munaf.ERP_SYSTEM.dtos.PurchaseDTO;
import com.munaf.ERP_SYSTEM.entities.Product;
import com.munaf.ERP_SYSTEM.entities.Purchase;
import com.munaf.ERP_SYSTEM.entities.Supplier;
import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.exceptions.ResourceNotFound;
import com.munaf.ERP_SYSTEM.repositories.MasterRepo;
import com.munaf.ERP_SYSTEM.services.PurchaseService;
import com.munaf.ERP_SYSTEM.utils.CommonPageResponse;
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.PageResponseModel;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@CacheConfig(cacheNames = "purchase")
public class PurchaseServiceIMPL implements PurchaseService {

    private final MasterRepo masterRepo;
    private final AccountServiceIMPL accountServiceIMPL;


    public PurchaseServiceIMPL(MasterRepo masterRepo, AccountServiceIMPL accountServiceIMPL) {
        this.masterRepo = masterRepo;
        this.accountServiceIMPL = accountServiceIMPL;
    }

    public User getUserWithId(Long userId) {
        return masterRepo.getUserRepo().findById(userId).
                orElseThrow(() -> new ResourceNotFound("User Not Exists With Id :" + userId));
    }

    public Supplier getSupplierWithId(Long supplierId, Long userId) {
        return masterRepo.getSupplierRepo().findByIdAndUserId(supplierId, userId).
                orElseThrow(() -> new ResourceNotFound("Supplier Not Exists With Id :" + supplierId));
    }

    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(value = "purchase", key = "#userId + '_' + #getAllPurchases"),
                    @CacheEvict(value = "product", key = "#userId + '_' + #getAllProducts")
            }
    )
    public ResponseModel purchaseProductFromSupplier(Long userId, Long supplierId, List<ProductDTO> productDTOS) {
        User user = getUserWithId(userId);
        Supplier supplier = getSupplierWithId(supplierId, userId);

        Long totalPurchasePrice = 0L;
        List<Product> products = new ArrayList<>();

        for (ProductDTO productDTO : productDTOS) {
            // Check if the product already exists in the database
            Product existingProduct = masterRepo.getProductRepo()
                    .findByProductNameAndUserId(productDTO.getProductName(), user.getId());

            if (existingProduct != null) {
                // Update stock if the product exists
                existingProduct.setProductStock(existingProduct.getProductStock() + productDTO.getProductStock());
                masterRepo.getProductRepo().save(existingProduct); // Persist changes
                products.add(existingProduct); // Add to the products list
            } else {
                // Create and save a new product
                Product newProduct = productDTO.productDtoToProduct();
                newProduct.setUser(user);
                masterRepo.getProductRepo().save(newProduct); // Persist the new product
                products.add(newProduct); // Add to the products list
            }

            // Update total price
            totalPurchasePrice += productDTO.getProductPurchasePrice()*productDTO.getProductStock();
        }

        // updating bank account
        accountServiceIMPL.withdrawFromAccount(userId, totalPurchasePrice);

        // Create a new purchase
        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setSupplier(supplier);
        purchase.setProducts(products);
        purchase.setPurchaseAmount(totalPurchasePrice);
        purchase.setPurchaseQuantity((long) products.size());

        // Save the purchase
        Purchase savedPurchase = masterRepo.getPurchaseRepo().save(purchase);

        // Update the Many-to-Many relationship (inverse side) // for unidirectional you can ignore this
//        for (Product product : products) {
//            product.getPurchases().add(savedPurchase);
//            masterRepo.getProductRepo().save(product); // Save changes to maintain the relationship
//        }

        return CommonResponse.OK(PurchaseDTO.purchaseToPurchaseDTO(savedPurchase));
    }


    @Override
    @Cacheable(key = "#userId + '_' + #getAllPurchases")
    public PageResponseModel getAllPurchases(Long userId, Integer pageNo, String sortBy) {
        User user = getUserWithId(userId);

        Pageable pageable = PageRequest.of(pageNo - 1, 10, Sort.by(sortBy));

        Page<Purchase> purchases = masterRepo.getPurchaseRepo().findByUserId(userId, pageable);

        List<PurchaseDTO> purchaseDTOS = purchases.getContent()
                .stream()
                .map(purchase -> PurchaseDTO.purchaseToPurchaseDTO(purchase))
                .toList();

        HashMap<String, Object> pageResult = new HashMap<>();
        pageResult.put("currentPage", pageNo);
        pageResult.put("totalPage", purchases.getTotalPages());
        pageResult.put("totalRecords", purchases.getTotalElements());

        return CommonPageResponse.OK(purchaseDTOS, pageResult);
    }


    @Override
    @Cacheable(key = "#userId + '_' + #purchaseId")
    public ResponseModel getPurchaseWithId(Long userId, Long purchaseId) {
        User user = getUserWithId(userId);
        Purchase purchase = masterRepo.getPurchaseRepo().findByIdAndUserId(purchaseId,userId)
                .orElseThrow(() -> new ResourceNotFound("Purchase Not Found With Id : " + purchaseId));

        return CommonResponse.OK(PurchaseDTO.purchaseToPurchaseDTO(purchase));
    }


}