package com.carely.sistema_consultas.repository;

import com.carely.sistema_consultas.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Medico findByEmail(String email);
}
