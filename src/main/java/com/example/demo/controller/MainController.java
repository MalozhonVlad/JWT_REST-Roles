package com.example.demo.controller;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MyUserDetailsService userDetailsService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public ResponseEntity<User> main() {
        User user = new User("Vlad", "Vlad");
//        User user = new User();
//        user.setUsername("3");
//        user.setPassword("3");
//        user.setAccountNonExpired(true);
//        user.setAccountNonLocked(true);
//        user.setCredentialsNonExpired(true);
//        user.setEnabled(true);
//        Set<Role> set = new HashSet<>();
////        set.add(Role.ADMIN);
//        set.add(Role.USER);
//        user.setAuthorities(set);
//        userDetailsService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User saveUser = userDetailsService.saveUser(user);
        return new ResponseEntity<>(saveUser,HttpStatus.CREATED);
    }


}
