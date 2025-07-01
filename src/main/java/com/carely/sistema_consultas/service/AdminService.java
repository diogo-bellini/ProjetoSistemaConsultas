package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.infra.seed.IAdminServiceSeed;
import com.carely.sistema_consultas.controller.adminController.IAdminServiceAdmin;
import com.carely.sistema_consultas.entity.Admin;
import com.carely.sistema_consultas.entity.AdminFactory;
import com.carely.sistema_consultas.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminServiceSeed, IAdminServiceAdmin {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminFactory adminFactory;


    public Admin carregarAdminEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public boolean existsById(Long id){
        return adminRepository.existsById(id);
    }

    public Admin createUsuario(){
        return (Admin) adminFactory.createUsuario();
    }

    public void save(Admin admin){
        adminRepository.save(admin);
    }

    public Admin findById(Long id){
        return adminRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Admin não encontrado com id: " + id));
    }

    public void deleteById(Long id){
        adminRepository.deleteById(id);
    }
}
