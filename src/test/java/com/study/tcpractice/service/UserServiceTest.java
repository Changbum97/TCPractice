package com.study.tcpractice.service;

import com.study.tcpractice.domain.dto.UserDto;
import com.study.tcpractice.domain.dto.UserResponse;
import com.study.tcpractice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.study.tcpractice.testModels.UserTestModel.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);

    UserService userService;

    /**
     * UserService 객체 생성 및 의존 주입
     */
    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("Save User Success Test")
    void saveUserSuccess() {
        // when
        when(userRepository.save(any())).thenReturn(SAVED_USER_1);

        // then
        UserDto response = userService.saveUser(USER_REQUEST);
        org.assertj.core.api.Assertions.assertThat(response.getId()).isEqualTo(1L);
        org.assertj.core.api.Assertions.assertThat(response.getName()).isEqualTo(USER_NAME_1);
    }

    @Test
    @DisplayName("User Find By Id Success Test")
    void userFindByIdSuccess() {
        // when
        when(userRepository.findById(any())).thenReturn(Optional.of(SAVED_USER_1));

        // then
        UserResponse response = userService.findById(1L);
        org.assertj.core.api.Assertions.assertThat(response.getId()).isEqualTo(1L);
        org.assertj.core.api.Assertions.assertThat(response.getName()).isEqualTo(USER_NAME_1);
    }

    @Test
    @DisplayName("User Find By Id Fail Test")
    void userFindByIdFail() {
        // when
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        // then
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
                    userService.findById(1L);
                })
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("id에 해당하는 User가 없습니다.");
    }
}