package com.toy.recruit.service.admin;

import com.toy.recruit.core.util.SecurityUtils;
import com.toy.recruit.domain.admin.Admin;
import com.toy.recruit.repository.admin.AdminRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    /*단일 조회*/
    public Admin findById(String id) {
        return adminRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Admin findLoginAdmin() {
        String id = SecurityUtils.getLoginUserId();
        return findById(id);
    }
}
