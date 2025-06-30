package com.carely.sistema_consultas.repository;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoConsultaRepository extends JpaRepository<AgendamentoConsulta, Long> {
}
