package com.luoguohua.finance.framework.filter;

import com.luoguohua.finance.framework.component.JwtToken;
import com.luoguohua.finance.framework.domain.dto.LoginUser;
import com.luoguohua.finance.framework.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/7/9 14:25
 * Content:
 */
public class JwtRequestFilter extends OncePerRequestFilter {

    private final String tokenPrefix = "Bearer ";

    private final JwtToken jwtToken;
    private final CustomUserDetailsService userDetailsService;

    public JwtRequestFilter(JwtToken jwtToken, CustomUserDetailsService userDetailsService) {
        this.jwtToken = jwtToken;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith(tokenPrefix)) {
            jwt = authorizationHeader.replace(tokenPrefix,"");
        }
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtToken.validateToken(jwt)) {
                LoginUser user = jwtToken.extractLoginUser(jwt);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null,
                                user.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
