package com.h2sm.smarthomebackend.api.auth.configuration;

import com.h2sm.smarthomebackend.api.auth.userdetails.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
    private final UserDetailsServiceImpl userDetailsService;
    @Value("${h2sm.app.jwtSecret}")
    private String jwtSecret;
    @Value("${h2sm.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && validateJwtToken(jwt)) {
                String username = getUserNameFromJwtToken(jwt);
                UserDetails userDetails;

                try {
                    userDetails = userDetailsService.loadUserByUsername(username);
                } catch (NullPointerException e) {
                    userDetails = userDetailsService.loadByHub(username);
                }

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: ", e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(TOKEN_PREFIX)) {
            return headerAuth.substring(7);
        } else if (StringUtils.hasText(request.getQueryString())) {
            return request.getQueryString().substring(6);
        }
        logger.error("Cannot parse JWT:" + headerAuth);
        return null;
    }

    private boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }


    private String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }

    private Key getKey() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret);
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }
}
