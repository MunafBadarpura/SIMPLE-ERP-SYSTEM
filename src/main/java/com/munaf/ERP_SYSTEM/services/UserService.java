package com.munaf.ERP_SYSTEM.services;

import com.munaf.ERP_SYSTEM.dtos.UserDTO;
import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;

public interface UserService {
    ResponseModel signupUser(UserDTO userDTO);

    ResponseModel loginUser(String email, String password);

    ResponseModel updateUser(Long userId, UserDTO updatedUser);

}
