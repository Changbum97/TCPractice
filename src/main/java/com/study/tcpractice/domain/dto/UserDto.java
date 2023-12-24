package com.study.tcpractice.domain.dto;

import com.study.tcpractice.domain.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String name;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .build();
    }

    public static UserDto of(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
