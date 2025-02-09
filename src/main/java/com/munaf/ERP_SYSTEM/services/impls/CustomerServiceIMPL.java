package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.dtos.CustomerDTO;
import com.munaf.ERP_SYSTEM.entities.Customer;
import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.exceptions.ResourceAlreadyExists;
import com.munaf.ERP_SYSTEM.exceptions.ResourceNotFound;
import com.munaf.ERP_SYSTEM.repositories.MasterRepo;
import com.munaf.ERP_SYSTEM.services.CustomerService;
import com.munaf.ERP_SYSTEM.utils.CommonPageResponse;
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.PageResponseModel;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;


@Service
@CacheConfig(cacheNames = "customer")
public class CustomerServiceIMPL implements CustomerService {

    private final MasterRepo masterRepo;

    public CustomerServiceIMPL(MasterRepo masterRepo) {
        this.masterRepo = masterRepo;
    }

    public User getUserWithId(Long userId) {
        return masterRepo.getUserRepo().findById(userId).
                orElseThrow(() -> new ResourceNotFound("User Not Exists With Id :" + userId));
    }

    @Override
    @Cacheable(key = "#userId + '_' + #customerId")
    public ResponseModel getCustomerById(Long userId, Long customerId) {
        User user = getUserWithId(userId);
        Customer customer = masterRepo.getCustomerRepo().findByIdAndUserId(customerId, userId).orElseThrow(() -> new ResourceNotFound("Customer Not Exists"));
        return CommonResponse.OK(CustomerDTO.customerToCustomerDto(customer));
    }

    @Override
    @Cacheable(key = "#userId + '_' + #customers")
    public PageResponseModel getAllCustomer(Long userId, Integer pageNo, String sortBy) {
        User user = getUserWithId(userId);
        Pageable pageable = PageRequest.of(pageNo-1,10, Sort.by(sortBy));

        Page<Customer> customers = masterRepo.getCustomerRepo().findAllByUserId(userId, pageable);

        List<CustomerDTO> customerDTOS = customers.getContent()
                .stream()
                .map(customer -> CustomerDTO.customerToCustomerDto(customer))
                .toList();

        HashMap<String, Object> pageResult = new HashMap<>();
        pageResult.put("currentPage", pageNo);
        pageResult.put("totalPage", customers.getTotalPages());
        pageResult.put("totalRecords", customers.getTotalElements());

        return CommonPageResponse.OK(customerDTOS,pageResult);

    }

    @Override
    @CacheEvict(key = "#userId + '_' + #customers")
    public ResponseModel createCustomer(Long userId, CustomerDTO customerDTO) {
        User user = getUserWithId(userId);

        Customer customer = customerDTO.customerDtoToCustomer();
        Boolean isEmailExists = masterRepo.getCustomerRepo().existsByEmailAndUserId(customer.getEmail(), userId);
        Boolean isPhoneExists = masterRepo.getCustomerRepo().existsByPhoneNoAndUserId(customer.getPhoneNo(), userId);
        if (isEmailExists || isPhoneExists)
            throw new ResourceAlreadyExists("Customer With This Email Or Phone Number already exists for this user");

        // saving customer
        customer.setUser(user);
        Customer savedCustomer = masterRepo.getCustomerRepo().save(customer);

        // saving user
        user.getCustomers().add(savedCustomer);
        masterRepo.getUserRepo().save(user);

        return CommonResponse.OK(CustomerDTO.customerToCustomerDto(savedCustomer));

    }

    @Override
    @CachePut(key = "#userId + '_' + #customerId")
    public ResponseModel updateCustomer(Long userId, Long customerId, CustomerDTO updatedCustomer) {
        User user = getUserWithId(userId);
        Customer customer = masterRepo.getCustomerRepo().findByIdAndUserId(customerId, userId).orElseThrow(() -> new ResourceNotFound("Customer Not Exists"));

        customer.setCustomerName(updatedCustomer.getCustomerName());
        customer.setPhoneNo(updatedCustomer.getPhoneNo());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setAddress(updatedCustomer.getAddress());

        // saving customer
        Customer savedCustomer = masterRepo.getCustomerRepo().save(customer);

        return CommonResponse.OK(CustomerDTO.customerToCustomerDto(savedCustomer));
    }

    @Override
    @CacheEvict(key = "#userId + '_' + #customerId")
    public ResponseModel deleteCustomer(Long userId, Long customerId) {
        User user = getUserWithId(userId);
        Customer customer = masterRepo.getCustomerRepo().findByIdAndUserId(customerId, userId).orElseThrow(() -> new ResourceNotFound("Customer Not Exists"));
        masterRepo.getCustomerRepo().deleteById(customerId);
        return CommonResponse.OK("CUSTOMER REMOVED");
    }
}
