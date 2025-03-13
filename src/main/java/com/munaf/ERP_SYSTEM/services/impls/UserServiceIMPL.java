package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.configs.security.JwtService;
import com.munaf.ERP_SYSTEM.dtos.UserDTO;
import com.munaf.ERP_SYSTEM.entities.Account;
import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.exceptions.ResourceAlreadyExists;
import com.munaf.ERP_SYSTEM.exceptions.ResourceNotFound;
import com.munaf.ERP_SYSTEM.repositories.MasterRepo;
import com.munaf.ERP_SYSTEM.services.UserService;
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceIMPL implements UserService {

    private final MasterRepo masterRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserServiceIMPL(MasterRepo masterRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.masterRepo = masterRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    //    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User userEntity =  masterRepo.getUserRepo().findByEmail(username)
//                .orElseThrow(() -> new ResourceAlreadyExists("Email not exists"));
//
//        return org.springframework.security.core.userdetails.User
//                .builder()
//                .username(userEntity.getEmail())
//                .password(userEntity.getPassword())
//                .roles()
//                .build();
//    }

    @Override
    @Transactional
    public ResponseModel signupUser(UserDTO userDTO) {
        User user = userDTO.UserDtoToUser();

        Boolean isUserAlreadyExist = masterRepo.getUserRepo().existsByEmailOrPhoneNo(user.getEmail(), user.getPhoneNo());
        if (isUserAlreadyExist) {
            throw new ResourceAlreadyExists("Email or PhoneNumber already exist");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); //encode password
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

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email,password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        User authenticatedUser = (User) authentication.getPrincipal();
        String jwtToken = jwtService.generateJwtToken(authenticatedUser);

        return CommonResponse.OK(jwtToken);


//        Optional<User> user = masterRepo.getUserRepo().findByEmail(email);
//
//        if (user.isEmpty()) {
//            throw new ResourceNotFound("user not found with email : " +email);
//        }
//
//        if (user.get().getPassword().equals(password)) {
//            return CommonResponse.OK("LOGIN SUCCESSFUL");
//        } else {
//            throw new InvalidInputException("password not valid");
//        }

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

    @Override
    public User getUserById(Long userId) {
        return masterRepo.getUserRepo().findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User Not Found With Id : "+ userId));
    }


}
