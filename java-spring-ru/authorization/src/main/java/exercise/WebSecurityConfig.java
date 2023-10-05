package exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.DELETE;

import exercise.model.UserRole;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    final UserDetailsServiceImpl userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // BEGIN
        http.csrf().disable();
        http
                .authorizeRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers(POST, "/users").permitAll()
                .requestMatchers(GET, "/users/**").hasAnyAuthority(UserRole.USER.toString(), UserRole.ADMIN.toString())
                .requestMatchers(DELETE, "/users/*").hasAuthority(UserRole.ADMIN.toString())
                .anyRequest().authenticated()
                .and().httpBasic();
        return http.build();
        // END
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
