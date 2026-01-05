package com.faizan.turfbooking.authservice.jwt;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.faizan.turfbooking.authservice.constant.Constant;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(Constant.AUTHORIZATION);

        // 1️⃣ Check header
        if (authHeader == null || !authHeader.startsWith(Constant.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2️⃣ Extract token
        String token = authHeader.substring(7); //removes "Bearer " 

        // 3️⃣ Validate token
        if (!jwtUtil.validateToken(token)) { // validate token : signature + expiry check
            filterChain.doFilter(request, response);
            return;
        }

        // 4️⃣ Extract user details
        String email = jwtUtil.extractEmail(token);
        String role = jwtUtil.extractRole(token);

        // 5️⃣ Create authentication object
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        Collections.emptyList() // roles added later
                );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        // 6️⃣ Set authentication in context // security context holder : tells Spring “user is logged in”
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 7️⃣ Continue request
        filterChain.doFilter(request, response);
    }
}
