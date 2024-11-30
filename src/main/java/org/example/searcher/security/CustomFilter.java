package org.example.searcher.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class CustomFilter extends OncePerRequestFilter {
    private final CustomUserDetailService customUserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Lấy ra email trong session
        String userEmail = (String) request.getSession().getAttribute("MY_SESSION");
        log.info(userEmail);

        if (userEmail == null) {
            filterChain.doFilter(request, response);
            return;
        }

        //Lấy ra thông tin user
        UserDetails userDetails = customUserDetailService.loadUserByUsername(userEmail);
        log.info("userDetails name = {}", userDetails.getUsername());
        log.info("userDetails roles = {}", userDetails.getAuthorities());

        //Tạo đối tượng phân quyền
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null
                , userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
