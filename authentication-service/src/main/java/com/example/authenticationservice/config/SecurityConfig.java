package com.example.authenticationservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    private final LogoutHandler logoutHandler;

    private final JdbcIndexedSessionRepository sessionRepository;

    private final ConcurrentSessionStrategy concurrentSessionStrategy;


    private static final String[] AUTH_WHITELIST = {
            "/auth/logout",
            "http://library-service/books/**",
            "http://course-service/courses/**",
            "http://course-service/participiants/**",
            "http://payment-service/payment/**"
    };


    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider, LogoutHandler logoutHandler, JdbcIndexedSessionRepository sessionRepository, ConcurrentSessionStrategy concurrentSessionStrategy) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
        this.logoutHandler = logoutHandler;
        this.sessionRepository = sessionRepository;
        this.concurrentSessionStrategy = concurrentSessionStrategy;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .httpBasic().disable()
                .authorizeHttpRequests()
                .requestMatchers(AUTH_WHITELIST)
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .sessionManagement()
                .maximumSessions(3)
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry(sessionRepository))
                .and()
                .sessionAuthenticationStrategy(concurrentSessionStrategy)
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                .invalidateHttpSession(true)
                .clearAuthentication(true);
        return http.build();
    }

    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

    @Bean
    public static SessionRegistry sessionRegistry(JdbcIndexedSessionRepository sessionRepository) {
        return new SpringSessionBackedSessionRegistry(sessionRepository);
    }
}
