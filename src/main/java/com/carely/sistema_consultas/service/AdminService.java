package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.controller.IAdminService;
import com.carely.sistema_consultas.entity.Admin;
import com.carely.sistema_consultas.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin carregarAdminEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
