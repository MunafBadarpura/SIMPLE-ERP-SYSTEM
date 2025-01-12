package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.User;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "username can not be blank")
    @Size(min = 3, max = 10, message = "username can be in the range of 3 to 10")
    private String username;

    @NotBlank(message = "phoneNo can not be blank")
    private String phoneNo;

    @Email(message = "email not valid")
    private String email;

    @NotBlank(message = "password can not be blank")
    @Size(min = 5, max = 15, message = "password can be in the range of 5 to 15")
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

        return userDTO;
    }
}
