package com.ssafy.togetherhomt.config;


import com.ssafy.togetherhomt.config.jwt.JwtAuthenticationFilter;
import com.ssafy.togetherhomt.config.jwt.JwtAuthorizationFilter;
import com.ssafy.togetherhomt.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Security-h2 충돌 방지
                .headers().frameOptions().sameOrigin()
                .and()

                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .authorizeRequests()
                .antMatchers("/user/account/sign-up/**").permitAll()
                .antMatchers("/user/account/reset-password").permitAll()
                .anyRequest().access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .and()
            .formLogin()
//                .loginPage("http://i6b206.p.ssafy.io/")
                .permitAll()
                .and()
            .httpBasic().disable()
            .addFilter(corsFilter)
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository));

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/webjars/**",
                "/user/account/sign-up/**",
                "/user/account/reset-password"
        );
        // ref: https://stackoverflow.com/a/37683455
    }

}
