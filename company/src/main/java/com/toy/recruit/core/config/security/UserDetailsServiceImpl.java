package com.toy.recruit.core.config.security;

import com.toy.recruit.domain.user.SessionUser;
import com.toy.recruit.domain.user.User;
import com.toy.recruit.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public SessionUser loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> findUser = userRepository.findById(userId);

        if (findUser.isEmpty()) {
            throw new UsernameNotFoundException("아이디가 존재하지 않습니다.");
        }

        User user = findUser.get();
        return SessionUser.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .name(user.getName())
                .role(user.getRole().name())
                .enabled(true)
                .build();
    }
}
