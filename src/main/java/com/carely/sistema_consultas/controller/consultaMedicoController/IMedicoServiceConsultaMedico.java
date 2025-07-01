package com.carely.sistema_consultas.controller.consultaMedicoController;

import com.carely.sistema_consultas.entity.Medico;

public interface IMedicoServiceConsultaMedico {
    public Medico carregarMedicoComConsultas(String email);
}
