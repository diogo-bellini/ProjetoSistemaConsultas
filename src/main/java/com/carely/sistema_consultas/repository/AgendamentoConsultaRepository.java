package com.carely.sistema_consultas.repository;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoConsultaRepository extends JpaRepository<AgendamentoConsulta, Long> {
    @EntityGraph(attributePaths = "paciente")
    AgendamentoConsulta findWithPacienteById(Long id);

    @EntityGraph(attributePaths = "medico")
    AgendamentoConsulta findWithMedicoById(Long id);

    List<AgendamentoConsulta> findByMedicoIdAndStatusNot(Long medicoId, String status);
}
