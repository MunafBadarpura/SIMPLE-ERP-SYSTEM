package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.dtos.CustomerDTO;
import com.munaf.ERP_SYSTEM.entities.Customer;
import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.exceptions.ResourceAlreadyExists;
import com.munaf.ERP_SYSTEM.exceptions.ResourceNotFound;
import com.munaf.ERP_SYSTEM.repositories.MasterRepo;
import com.munaf.ERP_SYSTEM.services.CustomerService;
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public ResponseModel getCustomerById(Long userId, Long customerId) {
        User user = getUserWithId(userId);
        Customer customer = masterRepo.getCustomerRepo().findByIdAndUserId(customerId, userId).orElseThrow(() -> new ResourceNotFound("Customer Not Exists"));
        return CommonResponse.OK(CustomerDTO.customerToCustomerDto(customer));
    }

    @Override
    public ResponseModel getAllCustomer(Long userId) {
        User user = getUserWithId(userId);
        List<Customer> customers = masterRepo.getCustomerRepo().findAllByUserId(userId);
        List<CustomerDTO> customerDTOS = customers
                .stream()
                .map(customer -> CustomerDTO.customerToCustomerDto(customer))
                .toList();

        return CommonResponse.OK(customerDTOS);

    }

    @Override
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
    public ResponseModel updateCustomer(Long userId, Long customerId, CustomerDTO updatedCustomer) {
        User user = getUserWithId(userId);
//        Customer customer = masterRepo.getCustomerRepo().findById(customerId).orElseThrow(() -> new ResourceNotFound("Customer Not Exists With Id :" + userId));

        Customer customer = masterRepo.getCustomerRepo().findByIdAndUserId(customerId, userId).orElseThrow(() -> new ResourceNotFound("Customer Not Exists"));

        if (updatedCustomer.getCustomerName() != null) customer.setCustomerName(updatedCustomer.getCustomerName());
        if (updatedCustomer.getPhoneNo() != null) customer.setPhoneNo(updatedCustomer.getPhoneNo());
        if (updatedCustomer.getEmail() != null) customer.setEmail(updatedCustomer.getEmail());
        if (updatedCustomer.getAddress() != null) {
            if (updatedCustomer.getAddress().getArea() != null) {
                customer.getAddress().setArea(updatedCustomer.getAddress().getArea());
            }
            if (updatedCustomer.getAddress().getCity() != null) {
                customer.getAddress().setCity(updatedCustomer.getAddress().getCity());
            }
            if (updatedCustomer.getAddress().getState() != null) {
                customer.getAddress().setState(updatedCustomer.getAddress().getState());
            }
            if (updatedCustomer.getAddress().getPinCode() != null) {
                customer.getAddress().setPinCode(updatedCustomer.getAddress().getPinCode());
            }
        }

        // saving customer
        Customer savedCustomer = masterRepo.getCustomerRepo().save(customer);

        return CommonResponse.OK(CustomerDTO.customerToCustomerDto(savedCustomer));
    }

    @Override
    public ResponseModel deleteCustomer(Long userId, Long customerId) {
        User user = getUserWithId(userId);
        Customer customer = masterRepo.getCustomerRepo().findByIdAndUserId(customerId, userId).orElseThrow(() -> new ResourceNotFound("Customer Not Exists"));
        masterRepo.getCustomerRepo().deleteById(customerId);
        return CommonResponse.OK("CUSTOMER REMOVED");
    }
}
