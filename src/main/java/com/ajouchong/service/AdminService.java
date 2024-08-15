package com.ajouchong.service;

import com.ajouchong.entity.Admin;
import com.ajouchong.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public Admin saveAdminContactInfo(Admin contactInfo) {
        List<Admin> existingAdmins = adminRepository.findAll();

        if (!existingAdmins.isEmpty()) {
            adminRepository.deleteAll();
        }

        return adminRepository.save(contactInfo);
    }

    public Optional<Admin> getAdminContactInfo() {
        List<Admin> admins = adminRepository.findAll();
        if (admins.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(admins.getFirst());
    }
}
