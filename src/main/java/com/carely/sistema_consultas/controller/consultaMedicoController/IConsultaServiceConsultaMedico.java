package com.carely.sistema_consultas.controller.consultaMedicoController;

import com.carely.sistema_consultas.entity.Consulta;

public interface IConsultaServiceConsultaMedico {
    public Consulta carregarConsultaComPaciente(Long id);
}
