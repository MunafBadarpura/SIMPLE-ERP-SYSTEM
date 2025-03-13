package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.dtos.UserDTO;
import com.munaf.ERP_SYSTEM.services.UserService;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseModel loginUser(@RequestParam String email, @RequestParam String password, HttpServletResponse httpServletResponse){
        ResponseModel responseModel = userService.loginUser(email,password);
        String jwtToken = responseModel.getData().toString();

        Cookie cookie = new Cookie("jwtToken", jwtToken);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);

        return responseModel;

    }

    // update
    @PutMapping("/update/{userId}")
    public ResponseModel updateUser(@RequestBody @Valid UserDTO updateUser, @PathVariable Long userId){
        return userService.updateUser(userId,updateUser);
    }


}
