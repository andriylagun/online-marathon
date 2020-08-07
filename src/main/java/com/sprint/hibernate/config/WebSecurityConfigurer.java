package com.sprint.hibernate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/form-login")
                .loginProcessingUrl("/login").defaultSuccessUrl("/home")
                .failureUrl("/form-login?error=true")
                .permitAll()
                .and().logout()
                .logoutUrl("/perform-logout")
                .logoutSuccessUrl("/form-login")
                .deleteCookies("JSESSIONID");
    }
}
