package com.example.ai_store.config;

package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .requireCsrfProtectionMatcher(request ->
                                !request.getRequestURI().startsWith("/auth/"))) // Tắt CSRF cho các endpoint bắt đầu với "/auth/"
            .authorizeRequests()
                .antMatchers("/auth/login", "/auth/register").permitAll() // Cho phép không cần xác thực tại login và register
                .anyRequest().authenticated() // Các yêu cầu còn lại yêu cầu xác thực
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();

        return http.build();
    }
}



