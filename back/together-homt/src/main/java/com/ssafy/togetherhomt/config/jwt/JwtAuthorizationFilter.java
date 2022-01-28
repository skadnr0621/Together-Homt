package com.ssafy.togetherhomt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtHeader = request.getHeader("Authorization");
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
//            response.sendError(400, "Bad Request : No Authorization header || JWT 토큰을 확인하세요.");
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");

        String email =
                JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build()
                        .verify(jwtToken)
                        .getClaim("email")  // getClaim("username") --> getClaim("email")
                        .asString();

        // 서명이 정상적으로 되었음.
        if (email != null) {    // username != null --> email != null
            System.out.println("email : " + email + "      [정상 서명됨]");
            User userEntity = userRepository.findByEmail(email);    // findByEmail(username) --> findByEmail(email)

            PrincipalDetails principalDetails = new PrincipalDetails(userEntity);

            // JWT 서명 - 정상적으로 서명되었다면 Authentication 객체 생성(강제)
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            // SecurityContextHolder.getContext() = security를 저장할 수 있는 session 획득
            // 강제로 시큐리티의 세션에 접근하여 Authentication 객체 저장.
            SecurityContextHolder.getContext().setAuthentication(authentication);
            SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            chain.doFilter(request, response);
        }
    }
}
