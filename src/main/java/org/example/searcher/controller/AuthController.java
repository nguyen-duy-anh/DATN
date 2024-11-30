package org.example.searcher.controller;

import org.example.searcher.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;
    @GetMapping
    public String login(){
        return "admin/auth/login";
    }
}
