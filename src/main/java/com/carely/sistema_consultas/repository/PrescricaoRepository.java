package com.carely.sistema_consultas.repository;

import com.carely.sistema_consultas.entity.Prescricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescricaoRepository extends JpaRepository<Prescricao, Long> {
}
