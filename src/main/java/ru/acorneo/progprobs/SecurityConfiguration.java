package ru.acorneo.progprobs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.acorneo.progprobs.repositories.UserRepository;
import ru.acorneo.progprobs.repositories.UserRolesRepository;
import ru.acorneo.progprobs.util.UserDetailsServiceImplementation;

@Configuration
public class SecurityConfiguration {
    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;

    public SecurityConfiguration(UserRepository userRepository, UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                auth -> auth.requestMatchers("/api/v1/users/create").permitAll()
                        .anyRequest().authenticated()
        )
                .httpBasic(Customizer.withDefaults())
                .anonymous(anonymous -> anonymous.disable());

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImplementation(userRepository, userRolesRepository);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
