package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.Account;
import com.munaf.ERP_SYSTEM.entities.User;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDTO implements Serializable {

    private Long id;

    // account
    private Long accountId;

    @NotBlank(message = "Username cannot be blank.")
    @Size(min = 3, max = 10, message = "Username must be between 3 and 10 characters.")
    private String username;

    @NotBlank(message = "Phone number cannot be blank.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits.")
    private String phoneNo;

    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Please provide a valid email address.")
    private String email;

    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 5, max = 15, message = "Password must be between 5 and 15 characters.")
    private String password;


    @Embedded
    @Valid
    private Address address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;





    public User UserDtoToUser() {
        User user = new User();
        user.setUsername(this.getUsername());
        user.setPhoneNo(this.getPhoneNo());
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        user.setAddress(this.getAddress());

        return user;
    }

    public static UserDTO UserToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPhoneNo(user.getPhoneNo());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAddress(user.getAddress());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        userDTO.setAccountId(user.getAccount().getId());

        return userDTO;
    }
}
