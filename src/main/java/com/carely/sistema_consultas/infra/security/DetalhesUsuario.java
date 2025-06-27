package com.carely.sistema_consultas.infra.security;

import com.carely.sistema_consultas.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class DetalhesUsuario implements UserDetails {

    private final Usuario usuario;

    public DetalhesUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role;
        if (usuario instanceof com.carely.sistema_consultas.entity.Admin) {
            role = "ROLE_ADMINISTRADOR";
        } else if (usuario instanceof com.carely.sistema_consultas.entity.Medico) {
            role = "ROLE_MEDICO";
        } else if (usuario instanceof com.carely.sistema_consultas.entity.Paciente) {
            role = "ROLE_PACIENTE";
        } else {
            role = "ROLE_USUARIO";
        }
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
