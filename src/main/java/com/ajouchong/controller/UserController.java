package com.ajouchong.controller;

import com.ajouchong.dto.UserRegistrationRequest;
import com.ajouchong.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ajouchong.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest request) {
        User user = userService.registerUser(request.getU_name(), request.getU_major(), request.getU_pwd(), request.getU_role());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{u_name}")
    public ResponseEntity<User> getUserByU_name(@PathVariable String u_name) {
        return userService.findUserByU_name(u_name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
