package com.study.tcpractice.service;

import com.study.tcpractice.domain.dto.UserDto;
import com.study.tcpractice.domain.dto.UserResponse;
import com.study.tcpractice.domain.entity.User;
import com.study.tcpractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto saveUser(UserDto userDto) {
        User savedUser = userRepository.save(userDto.toEntity());
        return UserDto.of(savedUser);
    }

    public UserResponse findById(Long userId) {
        User targetUser = userRepository.findById(userId)
                .orElseThrow(() -> new NullPointerException("id에 해당하는 User가 없습니다."));

        return UserResponse.of(targetUser);
    }

}
