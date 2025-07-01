package com.carely.sistema_consultas.controller.errorController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController{
    @GetMapping("")
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
