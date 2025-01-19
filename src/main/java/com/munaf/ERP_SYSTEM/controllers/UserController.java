package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.dtos.UserDTO;
import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.services.UserService;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Tag(name = "USER APIs", description = "With the help of this APIs user can signup,login and update their details")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // signup
    @PostMapping("/signup")
    public ResponseModel signupUser(@RequestBody @Valid UserDTO userDTO){
        return userService.signupUser(userDTO);
    }

    // login
    @PostMapping("/login")
    public ResponseModel loginUser(@RequestParam String email, @RequestParam String password){
        return userService.loginUser(email,password);
    }

    // update
    @PutMapping("/update/{userId}")
    public ResponseModel updateUser(@RequestBody @Valid UserDTO updateUser, @PathVariable Long userId){
        return userService.updateUser(userId,updateUser);
    }


}
