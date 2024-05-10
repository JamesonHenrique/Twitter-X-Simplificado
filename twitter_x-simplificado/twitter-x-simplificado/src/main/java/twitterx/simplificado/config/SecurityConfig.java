package twitterx.simplificado.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    private SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception{
        http
                .authorizeRequests( authorize -> authorize.anyRequest().authenticated())
                .csrf( (csrf) -> csrf.disable())
                .oauth2ResourceServer( (oauth2) -> oauth2.jwt(
                        Customizer.withDefaults()))
                .sessionManagement( (session) -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS))
        ;
                return http.build();
    }
}
