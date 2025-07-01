package com.carely.sistema_consultas.controller.pacienteController;

import com.carely.sistema_consultas.entity.Paciente;

public interface IPacienteServicePaciente {
    public Paciente carregarPacienteEmail(String email);
    void atualizarPerfil(Paciente paciente, String senha);
}
