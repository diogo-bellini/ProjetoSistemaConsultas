package com.carely.sistema_consultas.repository;

import com.carely.sistema_consultas.entity.Consulta;
import com.carely.sistema_consultas.entity.Medico;
import com.carely.sistema_consultas.entity.Paciente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByDataAndHoraAndMedicoAndPaciente(LocalDate data, LocalTime hora, Medico medico, Paciente paciente);
    @EntityGraph(attributePaths = "paciente")
    Consulta findWithPacienteById(Long id);

    @EntityGraph(attributePaths = {"diagnostico", "diagnostico.prescricoes"})
    Consulta findWithDiagnosticoPrescricoesById(Long id);

    @Query("SELECT c FROM Consulta c LEFT JOIN FETCH c.diagnostico d LEFT JOIN FETCH d.prescricoes WHERE c.id = :id")
    Consulta findWithDiagnosticoAndPrescricoesById(@Param("id") Long id);
}
