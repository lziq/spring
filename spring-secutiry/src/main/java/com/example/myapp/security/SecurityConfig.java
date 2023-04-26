package com.example.myapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles("employee")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("employee", "manager")
//                .build();
//
//        return new InMemoryUserDetailsManager(john, mary);
//    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager((dataSource));
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?;");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/").hasRole("EMPLOYEE")
                .requestMatchers("/leader/**").hasRole("MANAGER")
                .requestMatchers("/access-denied").permitAll()
                .and()
                .formLogin()
                .loginPage("/login") // Redirect all requests to this url if not authenticated yet
                .loginProcessingUrl("/authenticateTheUser") // Create a servlet with url "/authenticateTheUser" for authentication request
                .defaultSuccessUrl("/") // Redirect to this url once login is successful
                .permitAll()
                .and()
                .logout() // Create a servlet with url "/logout" by default for logout request
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");

        http.httpBasic();

        return http.build();
    }
}
