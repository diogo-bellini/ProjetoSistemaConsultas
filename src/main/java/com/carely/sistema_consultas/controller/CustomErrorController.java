package com.carely.sistema_consultas.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController{

    @GetMapping("/error")
    public String tratarError(HttpServletRequest request, Model model){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        if (status != null) {
            model.addAttribute("status", Integer.valueOf(status.toString()));
        }

        if (errorMessage != null && !errorMessage.toString().isEmpty()) {
            model.addAttribute("error", errorMessage.toString());
        }

        return "error";
    }
}
