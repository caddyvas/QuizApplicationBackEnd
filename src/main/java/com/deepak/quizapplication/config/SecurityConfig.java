package com.deepak.quizapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(customizer -> customizer.disable()) // cross-site request forgery - protect against csrf attacks
                // make user to login and get authorized - username and password. Do openLink operation for Signup
                .authorizeHttpRequests(request -> request.requestMatchers("/users/signup").permitAll().anyRequest().authenticated())
                //.formLogin(Customizer.withDefaults())
                // for postman client
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    /*
    AuthenticationProvider interface is responsible for authenticating user's credentials and returning an Authentication
    object. DaoAuthenticationProvider is one of the authentication providers used to authenticate users stored in database.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        System.out.println("Authentication Provide Bean Created!!");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }
}
