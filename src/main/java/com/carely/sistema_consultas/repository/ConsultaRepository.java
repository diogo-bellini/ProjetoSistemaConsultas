package com.carely.sistema_consultas.repository;

import com.carely.sistema_consultas.entity.Consulta;
import com.carely.sistema_consultas.entity.Medico;
import com.carely.sistema_consultas.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByDataAndHoraAndMedicoAndPaciente(LocalDate data, LocalTime hora, Medico medico, Paciente paciente);
}
