package com.mail.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security configuration
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsSecurityService userDetailsSecurityService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsSecurityService).passwordEncoder(passwordEncoderProducer());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .defaultSuccessUrl("/subscriptions")

                .and()
                    .logout()
                        .logoutSuccessUrl("/login")

                .and()
                    .authorizeRequests()
                        .antMatchers("/subscriptions")
                            .access("hasRole('ROLE_USER')")
                        .antMatchers("/**")
                            .access("permitAll")

                .and()
                    .csrf()
                        .ignoringAntMatchers("/h2-console/**")

                .and()
                    .headers()
                        .xssProtection()
                    .and()
                        .frameOptions()
                            .sameOrigin();
    }

    /**
     * Password encoder producer bean
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoderProducer() {
        return new BCryptPasswordEncoder();
    }
}
