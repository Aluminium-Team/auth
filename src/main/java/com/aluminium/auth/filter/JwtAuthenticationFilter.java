package com.aluminium.auth.filter;

import com.aluminium.auth.service.AccessService;
import com.aluminium.auth.service.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AccessService accessService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

            try {
                // Validate the token
                if (!jwtUtils.isExpired(token)) {
                    // Extract user information from token
                    UUID id = jwtUtils.extractUserId(token);

                    boolean isRefresh = false;
                    try {
                        jwtUtils.extractJti(token);
                        isRefresh = true;
                    } catch (Exception ignored) {}

                    if (isRefresh && accessService.validateRefreshToken(token) != null) {
                        id = jwtUtils.extractJti(token);
                    }

                    // If token is valid, set the authentication in the security context
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            id, null, null); // Replace `null` with user roles/authorities if needed
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // If token validation fails, clear the security context
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}
