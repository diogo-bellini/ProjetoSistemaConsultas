package com.carely.sistema_consultas.entity;

import org.springframework.stereotype.Component;

@Component
public class MedicoFactory implements UsuarioFactory {

    @Override
    public Usuario createUsuario() {
        return new Medico();
    }
}
