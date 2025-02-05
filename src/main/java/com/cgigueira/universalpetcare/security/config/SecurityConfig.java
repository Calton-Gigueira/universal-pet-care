package com.cgigueira.universalpetcare.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cgigueira.universalpetcare.security.jwt.JwtAuthFilter;
import com.cgigueira.universalpetcare.security.jwt.JwtEntryPoint;
import com.cgigueira.universalpetcare.security.user.PetCareUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private PetCareUserDetailsService userDetailsService;

  @Autowired
  private JwtAuthFilter jwtAuthFilter;

  @Autowired
  private JwtEntryPoint jwtEntryPoint;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .csrf(AbstractHttpConfigurer::disable)
      .cors(Customizer.withDefaults())
      .exceptionHandling(ex -> ex.authenticationEntryPoint(this.jwtEntryPoint))
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(request -> request.requestMatchers("/api/v1/auth/**").permitAll()
        .requestMatchers("/api/v1/users/**").hasRole("ADMIN")
        .anyRequest().authenticated())
      .authenticationProvider(this.authenticationProvider())
      .addFilterBefore(this.jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
      .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
    AuthenticationConfiguration authenticationConfiguration
  ) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(this.userDetailsService);
    authenticationProvider.setPasswordEncoder(this.passwordEncoder());
    return authenticationProvider;
  }
  
}
