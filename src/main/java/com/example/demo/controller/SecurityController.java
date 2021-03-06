package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
@PreAuthorize("hasAuthority('ADMIN')")
public class SecurityController {

    @GetMapping
    public String security(){
        return "Security page only for ADMIN";
    }

}
