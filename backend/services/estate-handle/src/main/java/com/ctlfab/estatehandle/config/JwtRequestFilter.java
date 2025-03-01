package com.ctlfab.estatehandle.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String token;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7);
        String role = jwtService.extractRole(token);

        if (role.equals("ROLE_USER")){
            filterChain.doFilter(request, response);
            return;
        }
        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

        if (jwtService.validateToken(token)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    jwtService.extractUsername(token),null,authorities
            );

            Object webAuthDetails = new WebAuthenticationDetailsSource().buildDetails(request);
            Map<String, Object> combinedDetails = new HashMap<>();
            Long userId = jwtService.extractId(token);
            combinedDetails.put("webAuthDetails", webAuthDetails);
            combinedDetails.put("userId", userId);
            authToken.setDetails(combinedDetails);

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }


        filterChain.doFilter(request, response);
    }
}
