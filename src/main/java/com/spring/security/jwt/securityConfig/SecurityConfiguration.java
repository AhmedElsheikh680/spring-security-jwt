package com.spring.security.jwt.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserPrincipalDetailsService userPrincipalDetailsService;

    @Autowired
    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // AuthenticationProvider
        auth.authenticationProvider(authenticationProvider());

        // In MemoryAuthentication
//        auth
//                .inMemoryAuthentication()
//                .withUser("Ahmed").password(passwordEncoder().encode("Ahmed123"))
////                .roles("ADMIN")
//                .authorities("ACCESS_BASIC_ALL", "ROLE_ADMIN")
//                .and()
//                .withUser("Ali").password(passwordEncoder().encode("Ali123"))
////                .roles("MANAGER")
//                .authorities("ACCESS_BASIC_MY", "ROLE_MANAGER")
//                .and()
//                .withUser("Mohamed").password(passwordEncoder().encode("Mohamed123"))
//                .roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/v1/main").permitAll()
                .antMatchers("/api/v1/profile").authenticated()
                .antMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .antMatchers("/api/v1/basic/allbasic").hasAuthority("ACCESS_BASIC_ALL")
                .antMatchers("/api/v1/basic/mybasic").hasAuthority("ACCESS_BASIC_MY")
                .antMatchers("/api/v1/management").hasAnyRole("ADMIN","MANAGER")
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/api/v1/login")
                .loginProcessingUrl("/signin")
                .usernameParameter("user")
                .passwordParameter("pass")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/api/v1/main");
//                .httpBasic();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
