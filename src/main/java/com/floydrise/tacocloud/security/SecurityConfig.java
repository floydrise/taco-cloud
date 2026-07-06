package com.floydrise.tacocloud.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

import com.floydrise.tacocloud.tacos.attributes.User;
import com.floydrise.tacocloud.tacos.data.UserRepository;

@Configuration
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException("User " + username + "not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**", "/ingredient/**", "/data-api/**"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/orders", "/design")
                        .hasRole("USER")
                        .anyRequest()
                        .permitAll())
                .oauth2Login(
                        oauth2 -> oauth2.loginPage("/login").successHandler((request, response, authentication) -> {
                            OAuth2User user = (OAuth2User) authentication.getPrincipal();
                            String username = user.getAttribute("login");
                            System.out.println(username);
                            var authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
                            Authentication newAuth = new UsernamePasswordAuthenticationToken(user, null, authorities);
                            SecurityContextHolder.getContext().setAuthentication(newAuth);
                            response.sendRedirect("/design");
                        }))
                .formLogin((formLogin) -> formLogin
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/login")
                        .defaultSuccessUrl("/design"))
                .logout((logout) -> logout.deleteCookies("remove")
                        .invalidateHttpSession(false)
                        .logoutSuccessUrl("/"))
                .build();
    }
}
