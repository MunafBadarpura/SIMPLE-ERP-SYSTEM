package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.dtos.SupplierDTO;
import com.munaf.ERP_SYSTEM.entities.Supplier;
import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.exceptions.ResourceAlreadyExists;
import com.munaf.ERP_SYSTEM.exceptions.ResourceNotFound;
import com.munaf.ERP_SYSTEM.repositories.MasterRepo;
import com.munaf.ERP_SYSTEM.services.SupplierService;
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceIMPL implements SupplierService {

    private final MasterRepo masterRepo;

    public SupplierServiceIMPL(MasterRepo masterRepo) {
        this.masterRepo = masterRepo;
    }

    private User getUserWithId(Long userId) {
        return masterRepo.getUserRepo().findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User Not Exists With Id :" + userId));
    }

    @Override
    public ResponseModel getSupplierById(Long userId, Long supplierId) {
        User user = getUserWithId(userId);
        Supplier supplier = masterRepo.getSupplierRepo().findByIdAndUserId(supplierId, userId)
                .orElseThrow(() -> new ResourceNotFound("Supplier Not Exists"));
        return CommonResponse.OK(SupplierDTO.supplierToSupplierDto(supplier));
    }

    @Override
    public ResponseModel getAllSuppliers(Long userId) {
        User user = getUserWithId(userId);
        List<Supplier> suppliers = masterRepo.getSupplierRepo().findAllByUserId(userId);
        List<SupplierDTO> supplierDTOS = suppliers.stream()
                .map(SupplierDTO::supplierToSupplierDto)
                .toList();
        return CommonResponse.OK(supplierDTOS);
    }

    @Override
    public ResponseModel createSupplier(Long userId, SupplierDTO supplierDTO) {
        User user = getUserWithId(userId);

        Supplier supplier = supplierDTO.supplierDtoToSupplier();
        Boolean isEmailExists = masterRepo.getSupplierRepo().existsByEmailAndUserId(supplier.getEmail(), userId);
        Boolean isPhoneExists = masterRepo.getSupplierRepo().existsByPhoneNoAndUserId(supplier.getPhoneNo(), userId);
        if (isEmailExists || isPhoneExists) {
            throw new ResourceAlreadyExists("Supplier With This Email Or Phone Number already exists for this user");
        }

        // Save supplier
        supplier.setUser(user);
        Supplier savedSupplier = masterRepo.getSupplierRepo().save(supplier);

        // Save user
        user.getSuppliers().add(savedSupplier);
        masterRepo.getUserRepo().save(user);

        return CommonResponse.OK(SupplierDTO.supplierToSupplierDto(savedSupplier));
    }

    @Override
    public ResponseModel updateSupplier(Long userId, Long supplierId, SupplierDTO updatedSupplier) {
        User user = getUserWithId(userId);
        Supplier supplier = masterRepo.getSupplierRepo().findByIdAndUserId(supplierId, userId)
                .orElseThrow(() -> new ResourceNotFound("Supplier Not Exists"));

        supplier.setSupplierName(updatedSupplier.getSupplierName());
        supplier.setPhoneNo(updatedSupplier.getPhoneNo());
        supplier.setEmail(updatedSupplier.getEmail());
        supplier.setAddress(updatedSupplier.getAddress());

        // Save supplier
        Supplier savedSupplier = masterRepo.getSupplierRepo().save(supplier);
        return CommonResponse.OK(SupplierDTO.supplierToSupplierDto(savedSupplier));
    }

    @Override
    public ResponseModel deleteSupplier(Long userId, Long supplierId) {
        User user = getUserWithId(userId);
        Supplier supplier = masterRepo.getSupplierRepo().findByIdAndUserId(supplierId, userId)
                .orElseThrow(() -> new ResourceNotFound("Supplier Not Exists"));
        masterRepo.getSupplierRepo().deleteById(supplierId);
        return CommonResponse.OK("SUPPLIER REMOVED");
    }

}
