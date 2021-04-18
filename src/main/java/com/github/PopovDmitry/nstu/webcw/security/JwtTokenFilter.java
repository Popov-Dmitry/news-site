package com.github.PopovDmitry.nstu.webcw.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    private final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("doFilter");
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
        logger.info("token: {}", token);

        try {
            if(token != null && jwtTokenProvider.validate(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                if(authentication != null) {
                    logger.info("setAuthentication");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        catch (JwtAuthenticationException exception) {
            logger.info("SecurityContextHolder.clearContext()");
            SecurityContextHolder.clearContext();
            ((HttpServletResponse) servletResponse).sendError(exception.getHttpStatus().value());
            throw new JwtAuthenticationException("JWT token is expired or invalid", HttpStatus.UNAUTHORIZED);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
