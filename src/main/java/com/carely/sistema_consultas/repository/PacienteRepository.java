package com.carely.sistema_consultas.repository;

import com.carely.sistema_consultas.entity.Paciente;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByEmail(String email);

    @EntityGraph(attributePaths = {"consultas", "consultas.medico"})
    Paciente findWithConsultasByEmail(String email);

    @EntityGraph(attributePaths = {"agendamentoConsultas", "agendamentoConsultas.medico"})
    Paciente findWithAgendamentosByEmail(String email);
}
