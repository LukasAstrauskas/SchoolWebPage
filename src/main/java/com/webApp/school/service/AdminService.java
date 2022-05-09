package com.webApp.school.service;

import com.webApp.school.model.Admin;
import com.webApp.school.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements MyService<Admin, Long> {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public Admin getById(Long aLong) {
        return adminRepository.getById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        adminRepository.deleteById(aLong);
    }

    @Override
    public void update(Admin admin) {

        adminRepository.save(admin);
    }
}
