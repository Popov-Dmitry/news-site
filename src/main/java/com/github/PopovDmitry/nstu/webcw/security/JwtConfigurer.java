package com.github.PopovDmitry.nstu.webcw.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenFilter jwtTokenFilter;

    private final Logger logger = LoggerFactory.getLogger(JwtConfigurer.class);

    @Autowired
    public JwtConfigurer(JwtTokenFilter jwtTokenFilter) { this.jwtTokenFilter = jwtTokenFilter; }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        logger.info("JwtConfigurer.configure");
        builder.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
