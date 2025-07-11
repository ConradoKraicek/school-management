package com.empresa.schoolmanagement.service;

import com.empresa.schoolmanagement.dto.LoginDTO;
import com.empresa.schoolmanagement.dto.TokenDTO;
import com.empresa.schoolmanagement.model.User;
import com.empresa.schoolmanagement.repository.UserRepository;
import com.empresa.schoolmanagement.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    public TokenDTO login(LoginDTO login) {
        User user = userRepository.findByUsername(login.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        if (!encoder.matches(login.getPassword(), user.getPassword()))
            throw new BadCredentialsException("Senha inválida");

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
                );
        String token = jwtProvider.generateToken(userDetails);

        if (token == null || token.isEmpty()) {
            throw new BadCredentialsException("Erro ao gerar token");
        }

        return new TokenDTO(token);
    }
}
