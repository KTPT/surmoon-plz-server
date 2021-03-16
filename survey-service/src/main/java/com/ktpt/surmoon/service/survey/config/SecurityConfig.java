package com.ktpt.surmoon.service.survey.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
            .antMatchers("/", "/login/**", "/logout", "/error", "/h2-console/**").permitAll()
            .antMatchers("/hi", "/login-success").authenticated()
            .antMatchers("/**").denyAll()
            .and()
            .oauth2Login()
            .loginPage("/hi")
            .userInfoEndpoint()
            .userService(customOAuth2UserService)
            .and()
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
