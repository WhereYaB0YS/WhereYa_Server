package com.where.WhereYouAt;


import com.where.WhereYouAt.domain.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthentificationFilter extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;

    public JwtAuthentificationFilter(
            AuthenticationManager authenticationManager,JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        Authentication authentification = getAuthentification(request);

        if(authentification != null){
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentification);
        }
        //token 유무 상관없이 dofilter는 항상 실행
        chain.doFilter(request,response);
    }

    private Authentication getAuthentification(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null){
            return null;
        }

        Claims claims = jwtUtil.getClaims(token.substring("Bearer ".length()));
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(claims,null);
        return authentication;
    }
}

