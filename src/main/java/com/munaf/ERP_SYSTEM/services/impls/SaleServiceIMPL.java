package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.dtos.ProductDTO;
import com.munaf.ERP_SYSTEM.dtos.SaleDTO;
import com.munaf.ERP_SYSTEM.dtos.SaleProductDTO;
import com.munaf.ERP_SYSTEM.entities.Customer;
import com.munaf.ERP_SYSTEM.entities.Product;
import com.munaf.ERP_SYSTEM.entities.Sale;
import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.exceptions.ResourceNotFound;
import com.munaf.ERP_SYSTEM.repositories.MasterRepo;
import com.munaf.ERP_SYSTEM.services.SaleService;
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceIMPL implements SaleService {

    private final MasterRepo masterRepo;

    public SaleServiceIMPL(MasterRepo masterRepo) {
        this.masterRepo = masterRepo;
    }

    public User getUserWithId(Long userId) {
        return masterRepo.getUserRepo().findById(userId).
                orElseThrow(() -> new ResourceNotFound("User Not Exists With Id :" + userId));
    }

    public Customer getCustomerWithId(Long customerId) {
        return masterRepo.getCustomerRepo().findById(customerId)
                .orElseThrow(() -> new ResourceNotFound("Customer Not Found With Id : " + customerId));
    }

    public Product getProductWithId(Long productId, Long userId) {
        return masterRepo.getProductRepo().findByIdAndUserId(productId, userId)
                .orElseThrow(() -> new ResourceNotFound("Product Not Exist With Id : " + productId));
    }

    @Override
    public ResponseModel saleProductToCustomer(Long userId, Long customerId, List<SaleProductDTO> saleProductDTOS) {
        User user = getUserWithId(userId);
        Customer customer = getCustomerWithId(customerId);
        Long totalSaleAmount = 0L;
        List<Product> products = new ArrayList<>();

        for (SaleProductDTO saleProductDTO : saleProductDTOS) {
            Product product = getProductWithId(saleProductDTO.getId(), userId);
            if (saleProductDTO.getProductQuantity()  > product.getProductStock())  return CommonResponse.OK("Stock Not Available For Product Id : " + saleProductDTO.getId());
        }

        for (SaleProductDTO saleProductDTO : saleProductDTOS) {
            Product product = getProductWithId(saleProductDTO.getId(), userId);

            totalSaleAmount += product.getProductPrice();
            products.add(product);
            product.setProductStock(product.getProductStock() - saleProductDTO.getProductQuantity());
            masterRepo.getProductRepo().save(product);
        }

        // creating a sale entry
        Sale sale = new Sale();
        sale.setUser(user);
        sale.setCustomer(customer);
        sale.setSaleAmount(totalSaleAmount);
        sale.setSaleQuantity((long) saleProductDTOS.size());
        sale.setProducts(products);
        Sale savedSale = masterRepo.getSaleRepo().save(sale);

        return CommonResponse.OK(SaleDTO.SaleToSaleDto(savedSale));
    }



    @Override
    public ResponseModel getAllSales(Long userId) {
        User user = getUserWithId(userId);
        List<Sale> sales = masterRepo.getSaleRepo().findByUserId(userId);
        List<SaleDTO> saleDTOS = sales.stream()
                .map(sale -> SaleDTO.SaleToSaleDto(sale))
                .toList();

        return CommonResponse.OK(sales);
    }

    @Override
    public ResponseModel getSaleById(Long userId, Long saleId) {
        User user = getUserWithId(userId);
        Sale sale = masterRepo.getSaleRepo().findByIdAndUserId(saleId, userId)
                .orElseThrow(() -> new ResourceNotFound("Sale Not Exists With Id" + saleId));

        return CommonResponse.OK(SaleDTO.SaleToSaleDto(sale));
    }


}













//User user = getUserWithId(userId);
//Customer customer = getCustomerWithId(customerId);
//
//List<Product> products = productDTOS.stream()
//        .map(productDTO -> {
//            Product product = productDTO.productDtoToProduct();
//
//
//        })
