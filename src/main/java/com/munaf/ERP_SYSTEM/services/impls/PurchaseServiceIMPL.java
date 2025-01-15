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
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceIMPL implements PurchaseService {

    private final MasterRepo masterRepo;

    public PurchaseServiceIMPL(MasterRepo masterRepo) {
        this.masterRepo = masterRepo;
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
    public ResponseModel purchaseProductFromSupplier(Long userId, Long supplierId, List<ProductDTO> productDTOS) {
        User user = getUserWithId(userId);
        Supplier supplier = getSupplierWithId(supplierId, userId);

        // saving purchase
        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setSupplier(supplier);
        Purchase savedPurchase = masterRepo.getPurchaseRepo().save(purchase);

        List<Product> productsForPurchase = new ArrayList<>();
        Long totalProductPrice = 0L;
        Long totalProductStock = 0L;


        // saving products
        List<Product> products = productDTOS.stream()
                .map(productDTO -> {
                    Product product = productDTO.productDtoToProduct();

                    Product isProductExist = masterRepo.getProductRepo().findByProductNameAndUserId(product.getProductName(), user.getId());
                    if (isProductExist != null) {
                        isProductExist.setProductStock(product.getProductStock() + isProductExist.getProductStock());
                        Product savedProduct = masterRepo.getProductRepo().save(isProductExist);
                        productsForPurchase.add(savedProduct); // adding products to purchase

                        // creating a copy product for set prev stock in purchase
                        Product productCopy = new Product();
                        productCopy.setProductPrice(savedProduct.getProductPrice());
                        productCopy.setProductStock(product.getProductStock());
                        return productCopy;
                    }
                    else {
                        product.setUser(user); //!
                        product.getPurchases().add(savedPurchase);//!

                        Product savedProduct = masterRepo.getProductRepo().save(product);
                        productsForPurchase.add(savedProduct); // adding products to purchase
                        return savedProduct;
                    }

                })
                .toList();



        for (Product p : products) { // finding total product price and stock
            totalProductPrice = totalProductPrice + p.getProductPrice();
            totalProductStock = totalProductStock + p.getProductStock();
        }
        purchase.setPurchaseAmount(totalProductPrice);
        purchase.setPurchaseQuantity(totalProductStock);
        purchase.setProducts(productsForPurchase);

        Purchase updatedPurchase  = masterRepo.getPurchaseRepo().save(purchase);

        return CommonResponse.OK(PurchaseDTO.purchaseToPurchaseDTO(updatedPurchase));

    }



    @Override
    public ResponseModel getAllPurchases(Long userId) {
        User user = getUserWithId(userId);
        List<Purchase> purchases = masterRepo.getPurchaseRepo().findByUserId(userId);
        List<PurchaseDTO> purchaseDTOS = purchases.stream()
                .map(purchase -> PurchaseDTO.purchaseToPurchaseDTO(purchase))
                .toList();

        return CommonResponse.OK(purchaseDTOS);
    }

    @Override
    public ResponseModel getPurchaseWithId(Long userId, Long purchaseId) {
        User user = getUserWithId(userId);
        Purchase purchase = masterRepo.getPurchaseRepo().findByIdAndUserId(purchaseId,userId)
                .orElseThrow(() -> new ResourceNotFound("Purchase Not Found With Id : " + purchaseId));

        return CommonResponse.OK(PurchaseDTO.purchaseToPurchaseDTO(purchase));
    }


}












/*

@Override
    public ResponseModel purchaseProductFromSupplier(Long userId, Long supplierId, List<ProductDTO> productDTOS) {
        User user = getUserWithId(userId);
        Supplier supplier = getSupplierWithId(supplierId);
        // saving purchase
        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setSupplier(supplier);
        Purchase savedPurchase = masterRepo.getPurchaseRepo().save(purchase);

        List<Product> productsForPurchase = new ArrayList<>();
        Long totalProductPrice = 0L;
        Long totalProductStock = 0L;


        // saving products
        List<Product> products = productDTOS.stream()
                .map(productDTO -> {
                    Product product = productDTO.productDtoToProduct();
                    product.setUser(user);
                    product.setPurchase(savedPurchase);

                    Product savedProduct = masterRepo.getProductRepo().save(product);

                    productsForPurchase.add(savedProduct); // adding products to purchase

                    return savedProduct;
                })
                .toList();

        for (Product p : products) { // finding total product price and stock
            totalProductPrice = totalProductPrice + p.getProductPrice();
            totalProductStock = totalProductStock + p.getProductStock();
        }
        purchase.setPurchaseAmount(totalProductPrice);
        purchase.setPurchaseQuantity(totalProductStock);
        purchase.setProducts(productsForPurchase);

        Purchase updatedPurchase  = masterRepo.getPurchaseRepo().save(purchase);

        return CommonResponse.OK(PurchaseDTO.purchaseToPurchaseDTO(updatedPurchase));

    }

** */
