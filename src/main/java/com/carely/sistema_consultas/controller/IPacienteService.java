package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.Paciente;

public interface IPacienteService {
    public Paciente carregarPacienteEmail(String email);
    public boolean existsById(Long id);
    public Paciente createUser();
    public void save(Paciente paciente);
    public Paciente findById(Long id);
    public void deleteById(Long id);
}
