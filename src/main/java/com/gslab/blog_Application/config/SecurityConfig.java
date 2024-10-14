package com.gslab.blog_Application.config;

import com.gslab.blog_Application.security.CustomUserDetailService;
import com.gslab.blog_Application.security.JwtAuthenticationEntryPoint;
import com.gslab.blog_Application.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter filter;
    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;
    @Autowired
    private CustomUserDetailService service;
//here we are trying to achieve/enable  basic authentication in spring boot security

         @Bean
         public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
             http
                     .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                     .authorizeHttpRequests(authorize -> authorize
                             .anyRequest().authenticated() // Require authentication for all requests
                     )
                     .exceptionHandling(exceptionHandling ->
                             exceptionHandling.authenticationEntryPoint(entryPoint) // Custom authentication entry point
                     )
                     .sessionManagement(sessionManagement ->
                             sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless session management
                     )
                     .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class) // Add custom filter
                     .httpBasic(withDefaults()); // Use HTTP Basic authentication

             return http.build(); // Return the built security configuration
         }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
        @Bean
        public PasswordEncoder passwordEncoder(){
         return  new BCryptPasswordEncoder();
        }

}
