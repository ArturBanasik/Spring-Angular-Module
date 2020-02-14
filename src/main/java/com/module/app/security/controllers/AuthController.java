package com.module.app.security.controllers;

import com.module.app.security.beans.AuthenticationBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @GetMapping(path = "/authenticate")
    public AuthenticationBean auth() {
        return new AuthenticationBean("You are authenticated!");
    }
}
