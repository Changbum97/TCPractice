package com.study.tcpractice.controller;

import com.study.tcpractice.domain.dto.UserDto;
import com.study.tcpractice.domain.dto.UserResponse;
import com.study.tcpractice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findAll(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }
}
