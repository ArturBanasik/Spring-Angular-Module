package com.module.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final String USER_ROLE = "USER";
    private final String USER_LOGIN = "username";
    private final String USER_PASS = "password";

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username(USER_LOGIN)
                .password(USER_PASS)
                .roles(USER_ROLE)
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/electronics/*").hasRole(USER_ROLE)
                .anyRequest().permitAll()
                .and()
                .httpBasic();

        http.headers().frameOptions().disable();
    }
}
