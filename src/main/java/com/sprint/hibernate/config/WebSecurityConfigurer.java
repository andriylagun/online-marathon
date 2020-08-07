package com.sprint.hibernate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth,  @Qualifier("userServiceImpl") UserDetailsService userDetailsService)
//            throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, AuthenticationProvider provider) throws Exception {
        auth.authenticationProvider(provider);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
