package com.carely.sistema_consultas.infra.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        String url = request.getContextPath();

        label:
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            String authority = grantedAuthority.getAuthority();

            switch (authority) {
                case "ROLE_ADMINISTRADOR":
                    url += "/admin/home";
                    break label;
                case "ROLE_PACIENTE":
                    url += "/paciente/home";
                    break label;
                case "ROLE_MEDICO":
                    url += "/medico/home";
                    break label;
            }
        }

        response.sendRedirect(url);
    }
}
