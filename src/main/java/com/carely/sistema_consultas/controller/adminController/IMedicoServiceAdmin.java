package com.carely.sistema_consultas.controller.adminController;

import com.carely.sistema_consultas.entity.Medico;

public interface IMedicoServiceAdmin {
    public Boolean existsById(long id);
    public Medico findById(long id);
    public void save(Medico medico);
    public void deleteById(Long id);
}
