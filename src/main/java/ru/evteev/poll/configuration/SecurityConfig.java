package ru.evteev.poll.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
@RequiredArgsConstructor
@Getter
@Setter
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_PERSON = "PERSON";

    private final DataSource dataSource;
    private final Environment env;
    private final Logger logger;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource);
    }

    //TODO Enable security after postman test
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        boolean securityEnabled = Boolean.parseBoolean(env.getProperty("poll.security.enabled"));
        logger.warn("Security Enabled: {}", securityEnabled);
        if (securityEnabled) {
            http.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/api/polls/**", "/api/answers/**").hasAnyRole(ROLE_PERSON, ROLE_ADMIN)
                    .antMatchers("/**").hasRole(ROLE_ADMIN)
                    .and().formLogin()
                        .defaultSuccessUrl("/api/persons", true).permitAll()
                    .and().logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                    .and().csrf()
            ;
        } else {
            http.authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .and().csrf()
                        .disable().authorizeRequests().anyRequest().permitAll()
            ;
        }
    }
}
