package org.example.searcher.service;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.searcher.exception.BadRequestException;
import org.example.searcher.model.request.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final HttpSession httpSession;

    public AuthService(AuthenticationManager authenticationManager, HttpSession httpSession) {
        this.authenticationManager = authenticationManager;
        this.httpSession = httpSession;
    }

    public String login(LoginRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            httpSession.setAttribute("MY_SESSION", authentication.getName());

            return "Login successful";
        }catch (Exception e) {
            log.info("Login failed" + e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }
}
