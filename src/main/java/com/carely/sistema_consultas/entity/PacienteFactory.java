package com.carely.sistema_consultas.entity;

import org.springframework.stereotype.Component;

@Component
public class PacienteFactory implements UsuarioFactory {
    public Usuario createUsuario() {
        return new Paciente();
    }
}
