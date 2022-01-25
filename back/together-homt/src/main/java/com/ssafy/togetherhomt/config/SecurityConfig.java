package com.ssafy.togetherhomt.config;


import com.ssafy.togetherhomt.config.jwt.JwtAuthenticationFilter;
import com.ssafy.togetherhomt.config.jwt.JwtAuthorizationFilter;
import com.ssafy.togetherhomt.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

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
                .antMatchers("/user/admin").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/main").access("hasRole('ROLE_USER')")
                .antMatchers("/article/**").permitAll()
                .anyRequest().permitAll()
                .and()
            .formLogin()
                .and()
            .httpBasic().disable()
            .addFilter(corsFilter)
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository));

    }
}
