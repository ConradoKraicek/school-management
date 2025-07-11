package com.empresa.schoolmanagement.controller;

import com.empresa.schoolmanagement.dto.LoginDTO;
import com.empresa.schoolmanagement.dto.TokenDTO;
import com.empresa.schoolmanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO login) {
        return ResponseEntity.ok(authService.login(login));
    }
}

