package com.carely.sistema_consultas.repository;

import com.carely.sistema_consultas.entity.Medico;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Medico findByEmail(String email);

    @EntityGraph(attributePaths = "agendamentoConsultas")
    Medico findWithAgendamentosByEmail(String email);

    @EntityGraph(attributePaths = "consultas")
    Medico findWithConsultasByEmail(String email);

    List<Medico> findByEspecialidade(String especialidade);

    @Query("SELECT DISTINCT m.especialidade FROM Medico m ORDER BY m.especialidade")
    List<String> findDistinctEspecialidades();
}
