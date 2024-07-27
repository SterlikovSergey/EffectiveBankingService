package by.st.effectivebankingservice.configuration;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    private final JWTTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(new AntPathRequestMatcher("/users/signup", "POST")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/users/{id}/phone", "PUT")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/users/{id}/phone", "DELETE")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/users/{id}/email", "PUT")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/users/{id}/email", "DELETE")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/auth", "POST")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/login", "GET")).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/api/**", "GET")).permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(new JWTAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
