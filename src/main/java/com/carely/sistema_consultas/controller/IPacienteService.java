package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.Paciente;

public interface IPacienteService {
    public Paciente carregarPacienteEmail(String email);
}
