package com.ktpt.surmoon.service.survey.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import com.ktpt.surmoon.service.survey.security.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        // security
        //     .authorizeRequests()
        //     .antMatchers(
        //         "/",
        //         "/error",
        //         "/favicon.ico",
        //         "/**/*.png",
        //         "/**/*.gif",
        //         "/**/*.svg",
        //         "/**/*.jpg",
        //         "/**/*.html",
        //         "/**/*.css",
        //         "/**/*.js"
        //     )
        //     .permitAll();
        // security
        //     .authorizeRequests()
        //     .anyRequest().authenticated()
        //     .and()
        //     .formLogin()
        //     .loginPage("/login")
        //     .loginProcessingUrl("/login")
        //     .defaultSuccessUrl("/")
        //     .failureUrl("/login/form?error")
        //     .permitAll();

        security
            .authorizeRequests()
            .antMatchers("/private").hasRole("ADMIN")
            .antMatchers("/public").permitAll()
            .antMatchers("/", "/login/**", "/logout", "/error", "/h2-console/**", "/hi").permitAll()
            .antMatchers("/**").denyAll()
            .and().oauth2Login()
            .userInfoEndpoint()
            .userService(customOAuth2UserService);

        security
            .headers()
            .frameOptions()
            .disable()
            .and()
            .authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()
            .and()
            .csrf()
            .ignoringAntMatchers("/h2-console/**");
    }
}
