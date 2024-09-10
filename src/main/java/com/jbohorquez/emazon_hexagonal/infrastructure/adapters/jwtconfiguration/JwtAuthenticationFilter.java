package com.jbohorquez.emazon_hexagonal.infrastructure.adapters.jwtconfiguration;


import io.jsonwebtoken.MalformedJwtException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String jwt;
        final String userName;

        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(SEVEN);

            userName = jwtService.extractUsername(jwt);

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    String extractRol = jwtService.extractRol(jwt);

                    List<GrantedAuthority> authorities = Collections.singletonList(
                            new SimpleGrantedAuthority(ROLE + extractRol)
                    );

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userName,
                            null,
                            authorities
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

             filterChain.doFilter(request, response);
         } catch (MalformedJwtException e) {
        response.setContentType(JSON);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getOutputStream().println(ERROR_JWT);
         }
    }
}

