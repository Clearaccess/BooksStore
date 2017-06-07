package com.university.books.store.configuration;

import com.university.books.store.util.LoginSuccessHandlerImpl;
import com.university.books.store.util.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Aleksandr on 6/4/2017.
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
     /*   auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");//dba have two roles.*/

        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        http.authorizeRequests()
                .antMatchers("/", "/books/**", "/book/**", "/login/", "/registration/", "/forget/").permitAll()
                .antMatchers("/account/**", "/bag/**", "/checkout/**").hasAnyRole("admin","customer")
                .and()
                    .formLogin().loginPage("/login/")
                    .usernameParameter("username").passwordParameter("password")
                    .successHandler(getLoginHandler())
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout/"))
                    .logoutSuccessHandler(getLogoutHandler())
                .and()
                    .exceptionHandling().accessDeniedPage("/notAccess/");

    }

    @Bean
    public LoginSuccessHandlerImpl getLoginHandler(){
        return new LoginSuccessHandlerImpl();
    }

    @Bean
    public LogoutSuccessHandler getLogoutHandler(){
        return new LogoutSuccessHandlerImpl();
    }
}
