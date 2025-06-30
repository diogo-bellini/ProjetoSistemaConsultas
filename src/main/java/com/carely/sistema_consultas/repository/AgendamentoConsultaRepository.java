package com.carely.sistema_consultas.repository;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AgendamentoConsultaRepository extends JpaRepository<AgendamentoConsulta, Long> {
    @EntityGraph(attributePaths = "paciente")
    AgendamentoConsulta findWithPacienteById(Long id);

    @EntityGraph(attributePaths = "medico")
    AgendamentoConsulta findWithMedicoById(Long id);

    List<AgendamentoConsulta> findByMedicoIdAndStatusNot(Long medicoId, String status);

    @Query("SELECT a FROM AgendamentoConsulta a " +
            "WHERE a.status = 'Confirmada' AND " +
            "(a.data < :hoje OR (a.data = :hoje AND a.hora <= :agora)) AND " +
            "a.processado = false")
    List<AgendamentoConsulta> buscarAgendamentosProntosNaoProcessados(@Param("hoje") LocalDate hoje, @Param("agora") LocalTime agora);
}
