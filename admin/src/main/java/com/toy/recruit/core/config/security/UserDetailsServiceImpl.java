package com.toy.recruit.core.config.security;

import com.toy.recruit.domain.admin.Admin;
import com.toy.recruit.domain.admin.SessionUser;
import com.toy.recruit.repository.admin.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public SessionUser loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Admin> findAdmin = adminRepository.findById(userId);

        if (findAdmin.isEmpty()) {
            throw new UsernameNotFoundException("아이디가 존재하지 않습니다.");
        }

        Admin admin = findAdmin.get();
        SessionUser sessionUser = SessionUser.builder()
                .userId(admin.getAdminId())
                .password(admin.getPassword())
                .name(admin.getName())
                .role(admin.getRole().name())
                .enabled(true)
                .build();

        return sessionUser;
    }
}
