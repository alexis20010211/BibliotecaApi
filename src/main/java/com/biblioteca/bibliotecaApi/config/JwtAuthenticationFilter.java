package com.biblioteca.bibliotecaApi.config;

import com.biblioteca.bibliotecaApi.security.JwtService;
import com.biblioteca.bibliotecaApi.security.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService uds;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService uds){
        this.jwtService = jwtService;
        this.uds = uds;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        String token = null;
        if (header != null && header.startsWith("Bearer ")) token = header.substring(7);

        if (token != null && jwtService.validateToken(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
            String username = jwtService.getUsernameFromToken(token);
            UserDetails ud = uds.loadUserByUsername(username);
            var auth = new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
