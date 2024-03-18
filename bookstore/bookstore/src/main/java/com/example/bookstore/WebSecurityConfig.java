package com.example.bookstore;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.bookstore.domain.UserDetailServiceImpl;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
        // käynnistäessä tulostaa salasanan konsoliin ja username = user

        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers("/", "/home").permitAll() // näihi ei tarttee tunnuksia
                                                // .requestMatchers(antMatcher("/admin/**”)).hasRole("ADMIN") -> päästää
                                                // vaan
                                                // jos on admin rooli
                                                .anyRequest().authenticated())
                                .formLogin(formLogin -> formLogin
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/allbooks", true) // mihi mennää login jälkee
                                                .permitAll())
                                .logout(logout -> logout
                                                .permitAll());
                return http.build();
        }

        @Autowired
        private UserDetailServiceImpl userDetailsService;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        }

        /*
         * Spring Security
         *  Create in-memory users
         *  This is only for testing and demo purposes (Security configuration class)
         *  You can add multiple user using Collection<UserDetails>
         */

        @Bean
        public UserDetailsService userDetailsService() {
                UserDetails user = User.withDefaultPasswordEncoder()
                                .username("user")
                                .password("password")
                                .roles("USER")
                                .build();
                List<UserDetails> users = new ArrayList();
                users.add(user);
                return new InMemoryUserDetailsManager(users);
        }

}
