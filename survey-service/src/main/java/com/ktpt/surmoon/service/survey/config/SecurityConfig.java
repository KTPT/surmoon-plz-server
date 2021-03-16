package com.ktpt.surmoon.service.survey.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.ktpt.surmoon.service.survey.security.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
            .authorizeRequests()
            .antMatchers("/", "/login/**", "/logout", "/error", "/h2-console/**").permitAll()
            .antMatchers("/hi", "/login-success").authenticated()
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
