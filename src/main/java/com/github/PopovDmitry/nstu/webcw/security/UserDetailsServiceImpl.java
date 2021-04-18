package com.github.PopovDmitry.nstu.webcw.security;

import com.github.PopovDmitry.nstu.webcw.model.User;
import com.github.PopovDmitry.nstu.webcw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    public UserDetailsServiceImpl(UserService userService) { this.userService = userService; }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("loadUserByUsername {}", email);

        User user = userService.getUser(email).orElseThrow(() -> new UsernameNotFoundException("User is not found"));
        return SecurityUser.fromUser(user);
    }
}
