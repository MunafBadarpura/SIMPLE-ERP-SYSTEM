package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.exceptions.ResourceAlreadyExists;
import com.munaf.ERP_SYSTEM.exceptions.ResourceNotFound;
import com.munaf.ERP_SYSTEM.repositories.MasterRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceIMPL implements UserDetailsService {


    private final MasterRepo masterRepo;

    public UserDetailsServiceIMPL(MasterRepo masterRepo) {
        this.masterRepo = masterRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return masterRepo.getUserRepo().findByEmail(username)
                .orElseThrow(() -> new ResourceAlreadyExists("Email not exists"));
    }


    public User getUserById(Long userId) {
        return masterRepo.getUserRepo().findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User Not Found With Id : "+ userId));
    }
}
