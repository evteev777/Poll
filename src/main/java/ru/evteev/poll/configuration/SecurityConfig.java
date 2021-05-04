package ru.evteev.poll.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_PERSON = "PERSON";

    @Autowired
    protected DataSource dataSource;

    @Autowired
    AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource);
    }

    //TODO Enable security after test
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/api/polls/**").hasAnyRole(ROLE_PERSON, ROLE_ADMIN)
//                .antMatchers("/**").hasRole(ROLE_ADMIN)
//                .and().formLogin()
//                    .defaultSuccessUrl("/api/persons", true).permitAll()
//                .and().logout()
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/")
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID")
//                .and().exceptionHandling()
//                    .accessDeniedHandler(accessDeniedHandler)
//                .and().csrf()
        ;
    }
}
