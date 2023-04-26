package com.example.myapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class MyController {
    @GetMapping("/")
    public String showHome(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName())
                .addAttribute("role", authentication.getAuthorities())
                .addAttribute("isManager", authentication.getAuthorities().stream().anyMatch(authority -> Objects.equals(authority.getAuthority(), "ROLE_MANAGER")));

        return "home";
    }

    @GetMapping("/login")
    public String showMyLoginPage() {
        return "login-page";
    }

    @GetMapping("/leader")
    public String showLeaderPage() {
        return "leader";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
