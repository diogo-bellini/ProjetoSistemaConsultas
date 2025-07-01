package com.carely.sistema_consultas.infra.seed;

import com.carely.sistema_consultas.entity.Paciente;

public interface IPacienteServiceSeed {
    public Paciente carregarPacienteEmail(String email);
    public void save(Paciente paciente);
    public Paciente findById(Long id);

}
