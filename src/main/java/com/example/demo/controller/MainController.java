package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.UserDto;
import com.example.demo.jwtUtil.JwtUtil;
import com.example.demo.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MyUserDetailsService userDetailsService;
    //    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtils;

    @GetMapping
    public ResponseEntity<User> main() {
        User user = new User("Vlad", "Vlad");

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User saveUser = userDetailsService.saveUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(HttpServletResponse response,
                                                       @RequestBody UserDto userDto) throws Exception {
        UsernamePasswordAuthenticationToken authenticationTokenUser =
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
        try {
            authenticationManager.authenticate(authenticationTokenUser);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userDto.getUsername());

        String jwt = jwtTokenUtils.generateToken(userDetails);

        response.setHeader("Authorization", "Bearer " + jwt);

        return new ResponseEntity<>("You got token,see in Headers", HttpStatus.OK);
    }


}
