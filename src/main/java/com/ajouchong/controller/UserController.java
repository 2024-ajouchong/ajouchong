package com.ajouchong.controller;

import com.ajouchong.dto.UserRegistrationRequest;
import com.ajouchong.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ajouchong.service.UserServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest request) {
        User user = userServiceImpl.registerUser(request.getU_name(), request.getU_major(), request.getU_pwd(), request.getU_email(), request.getU_role());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{u_name}")
    public ResponseEntity<User> getUserByU_name(@PathVariable String u_name) {
        return userServiceImpl.findUserByU_name(u_name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
