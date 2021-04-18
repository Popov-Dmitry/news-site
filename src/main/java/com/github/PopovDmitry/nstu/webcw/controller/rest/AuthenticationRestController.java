package com.github.PopovDmitry.nstu.webcw.controller.rest;

import com.github.PopovDmitry.nstu.webcw.dto.AuthenticationRequestDTO;
import com.github.PopovDmitry.nstu.webcw.dto.JwtTokenDTO;
import com.github.PopovDmitry.nstu.webcw.model.User;
import com.github.PopovDmitry.nstu.webcw.security.JwtAuthenticationException;
import com.github.PopovDmitry.nstu.webcw.security.JwtTokenProvider;
import com.github.PopovDmitry.nstu.webcw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@RestController
@RequestMapping("/dev/api/public/auth")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationRestController.class);

    public AuthenticationRestController(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        try {
            logger.info("login");
            //AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO(inputEmail, inputPassword);
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    authenticationRequestDTO.getEmail(), authenticationRequestDTO.getPassword()));
            User user = userService.getUser(authenticationRequestDTO.getEmail()).orElseThrow(() ->
                    new UsernameNotFoundException("User is not found"));
            if(authenticationRequestDTO.getPassword().equals(user.getPassword())) {
                String token = jwtTokenProvider.createToken(authenticationRequestDTO.getEmail(), user.getRole().name());
                Map<Object, Object> response = new HashMap<>();
                response.put("email", authenticationRequestDTO.getEmail());
                response.put("token", token);
//                HttpHeaders headers = new HttpHeaders();
//                headers.add(HttpHeaders.AUTHORIZATION, token);
//                headers.add(HttpHeaders.LOCATION, "http://localhost:8080/news");
//                return new ResponseEntity<>("", headers, HttpStatus.OK);
                logger.info("login:ok");
                return ResponseEntity.ok(response);
            }
            logger.info("login:failure");
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
        catch (AuthenticationException exception) {
            logger.info("login:failure");
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(httpServletRequest, httpServletResponse, null);
    }

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody JwtTokenDTO jwtTokenDTO) {
        logger.info("auth");
        Hashtable<String, Object> hashtable = new Hashtable<>();
        try {
            if (jwtTokenProvider.validate(jwtTokenDTO.getToken())) {
                hashtable.put("isValid", true);
                hashtable.put("name", userService.getUser(
                        jwtTokenProvider.getUsername(jwtTokenDTO.getToken()))
                        .get()
                        .getFirstName());
            }
        }
        catch (JwtAuthenticationException exception) {
            hashtable.put("isValid", false);
            hashtable.put("name", "");
        }

        return ResponseEntity.ok(hashtable);
    }
}
