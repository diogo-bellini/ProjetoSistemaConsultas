package com.carely.sistema_consultas.repository;

import com.carely.sistema_consultas.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    @Query("SELECT COUNT(c) > 0 FROM Consulta c WHERE c.data = :data AND c.hora = :hora AND c.medico.id = :medicoId AND c.paciente.id = :pacienteId")
    boolean existsConsulta(@Param("data") LocalDate data,
                           @Param("hora") LocalTime hora,
                           @Param("medicoId") Long medicoId,
                           @Param("pacienteId") Long pacienteId);
}
