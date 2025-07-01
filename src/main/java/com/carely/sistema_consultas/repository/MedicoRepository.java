package com.carely.sistema_consultas.repository;

import com.carely.sistema_consultas.entity.Medico;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Medico findByEmail(String email);

    @EntityGraph(attributePaths = "agendamentoConsultas")
    Medico findWithAgendamentosByEmail(String email);

    @EntityGraph(attributePaths = "consultas")
    Medico findWithConsultasByEmail(String email);
}
