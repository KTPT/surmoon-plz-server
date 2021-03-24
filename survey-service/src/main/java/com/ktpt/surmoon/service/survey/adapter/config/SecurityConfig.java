package com.ktpt.surmoon.service.survey.adapter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
            .authorizeRequests()
            .antMatchers("/", "/login/**", "/logout", "/error", "/h2-console/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2Login()
            .defaultSuccessUrl("/login-success", true);

        security
            .headers()
            .frameOptions()
            .disable()
            .and()
            .csrf()
            .ignoringAntMatchers("/h2-console/**");
    }
}
