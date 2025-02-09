package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.dtos.UserDTO;
import com.munaf.ERP_SYSTEM.entities.Account;
import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.exceptions.InvalidInputException;
import com.munaf.ERP_SYSTEM.exceptions.ResourceAlreadyExists;
import com.munaf.ERP_SYSTEM.exceptions.ResourceNotFound;
import com.munaf.ERP_SYSTEM.repositories.MasterRepo;
import com.munaf.ERP_SYSTEM.services.UserService;
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {

    private final MasterRepo masterRepo;

    public UserServiceIMPL(MasterRepo masterRepo) {
        this.masterRepo = masterRepo;
    }

    @Override
    @Transactional
    public ResponseModel signupUser(UserDTO userDTO) {
        User user = userDTO.UserDtoToUser();

        Boolean isUserAlreadyExist = masterRepo.getUserRepo().existsByEmailOrPhoneNo(user.getEmail(), user.getPhoneNo());
        if (isUserAlreadyExist) {
            throw new ResourceAlreadyExists("Email or PhoneNumber already exist");
        }

        User savedUser = masterRepo.getUserRepo().save(user); // saving a user

        Account account = new Account(); // Create and associate account
        account.setUser(savedUser);
        Account savedAccount = masterRepo.getAccountRepo().save(account);


        savedUser.setAccount(account);
        masterRepo.getUserRepo().save(savedUser);
        return CommonResponse.OK(UserDTO.UserToUserDTO(savedUser));
    }

    @Override
    public ResponseModel loginUser(String email, String password) {
        Optional<User> user = masterRepo.getUserRepo().findByEmail(email);

        if (user.isEmpty()) {
            throw new ResourceNotFound("user not found with email : " +email);
        }

        if (user.get().getPassword().equals(password)) {
            return CommonResponse.OK("LOGIN SUCCESSFUL");
        } else {
            throw new InvalidInputException("password not valid");
        }

    }

    @Override
    public ResponseModel updateUser(Long userId, UserDTO updatedUser) {
        User prevUser = masterRepo.getUserRepo().findById(userId).orElseThrow(() -> new ResourceNotFound("User not found with id " + userId));

        if (updatedUser.getUsername() != null) prevUser.setUsername(updatedUser.getUsername());
        if (updatedUser.getPhoneNo() != null) prevUser.setPhoneNo(updatedUser.getPhoneNo());
        if (updatedUser.getEmail() != null) prevUser.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != null) prevUser.setPassword(updatedUser.getPassword());
        if (updatedUser.getAddress() != null) {
            if (updatedUser.getAddress().getArea() != null) {
                prevUser.getAddress().setArea(updatedUser.getAddress().getArea());
            }
            if (updatedUser.getAddress().getCity() != null) {
                prevUser.getAddress().setCity(updatedUser.getAddress().getCity());
            }
            if (updatedUser.getAddress().getState() != null) {
                prevUser.getAddress().setState(updatedUser.getAddress().getState());
            }
            if (updatedUser.getAddress().getPinCode() != null) {
                prevUser.getAddress().setPinCode(updatedUser.getAddress().getPinCode());
            }
        }

        User savedUser = masterRepo.getUserRepo().save(prevUser);
        return CommonResponse.OK(UserDTO.UserToUserDTO(savedUser));

    }

}
