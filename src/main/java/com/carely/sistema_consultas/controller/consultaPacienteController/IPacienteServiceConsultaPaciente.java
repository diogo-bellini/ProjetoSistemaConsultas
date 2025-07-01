package com.carely.sistema_consultas.controller.consultaPacienteController;

import com.carely.sistema_consultas.entity.Paciente;

public interface IPacienteServiceConsultaPaciente {
    public Paciente carregarPacienteComConsultas(String email);
    public Paciente carregarPacienteComAgendamentos(String email);
}
