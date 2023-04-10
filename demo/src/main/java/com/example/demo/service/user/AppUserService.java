package com.example.demo.service.user;

import com.example.demo.model.AppUser;
import com.example.demo.repository.IAppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AppUserService implements IAppUserService, UserDetailsService {

    @Autowired
    private IAppUserRepo appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepo.findByName(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(appUser.getAppRole());
        UserDetails userDetails = new User(
                appUser.getName(),
                appUser.getPassword(),
                authorities
        );
        return userDetails;
    }
}