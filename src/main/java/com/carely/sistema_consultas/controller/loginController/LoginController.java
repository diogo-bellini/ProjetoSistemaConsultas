package com.carely.sistema_consultas.controller.loginController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String redirectLogin() {
        return "redirect:/login";
    }
}
