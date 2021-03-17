package com.spring.security.jwt.securityConfig;

import com.spring.security.jwt.repo.UserRepo;
import com.spring.security.jwt.securityConfig.jwt.JwtAuthenticationFilter;
import com.spring.security.jwt.securityConfig.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserPrincipalDetailsService userPrincipalDetailsService;
    private UserRepo userRepo;

    @Autowired
    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService, UserRepo userRepo) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.userRepo = userRepo;
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
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepo))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers("/api/v1/myAdmin").hasRole("ADMIN")
                .antMatchers("/api/v1/myAdminOrManager").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/api/v1/myAdminOrManagerOrUser").hasAnyRole("ADMIN","MANAGER","USER")
                .anyRequest().authenticated();
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
