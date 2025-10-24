package com.rrhh.sistema.config;

import com.rrhh.sistema.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
                .csrf(csrf -> csrf.disable())
		        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		        .authorizeHttpRequests(authz -> authz
                    .requestMatchers("/api/auth/**").permitAll()  // ✅ Login sin autenticación
                    .requestMatchers("/api/users/**").authenticated()  // ✅ Requiere token
                    .requestMatchers("/api/employee/**").authenticated()  // ✅ Requiere token
				    .anyRequest().authenticated()
		).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
