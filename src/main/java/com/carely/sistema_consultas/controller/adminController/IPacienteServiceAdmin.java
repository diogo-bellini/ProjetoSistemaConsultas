package com.carely.sistema_consultas.controller.adminController;

import com.carely.sistema_consultas.entity.Paciente;

public interface IPacienteServiceAdmin {
    public boolean existsById(Long id);
    public Paciente findById(Long id);
    public void save(Paciente paciente);
    public void deleteById(Long id);
}
