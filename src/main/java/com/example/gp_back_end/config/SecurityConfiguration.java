package com.example.gp_back_end.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.gp_back_end.user.Role.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/userRole/**").permitAll()
                        .requestMatchers("/api/v1/privilegedUsers/**").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()// Adjust the request matchers as needed
                        .requestMatchers("/forms/**").permitAll()
                        .requestMatchers("/students/**").permitAll()
                        .requestMatchers("/api/cloaks/**").permitAll()
                        .requestMatchers("/registration/**").permitAll()

                        .requestMatchers("/course/**").permitAll()
//                        .requestMatchers("/api/v1/student/{id}/**").permitAll()
                        .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
                        .requestMatchers("/api/v1/student/**").permitAll()
                        .requestMatchers("/api/v1/lecturer/**").hasAnyRole(LECTURER.name(), ADMIN.name())
                        .requestMatchers("/send-email").permitAll()
                        .requestMatchers("/notifications").permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
