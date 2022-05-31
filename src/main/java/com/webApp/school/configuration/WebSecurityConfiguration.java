package com.webApp.school.configuration;

import com.webApp.school.service.SchoolUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService userDetails() {
        return new SchoolUserDetailService();
    }

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails())
                .passwordEncoder(encoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/", "/mainHall").permitAll()
                .antMatchers("/profile").authenticated()
                .antMatchers("/student/Courses").hasRole("STUDENT")
                .antMatchers("/teacher/Courses").hasRole("TEACHER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/Tasks", "/Tasks/*").hasAnyRole("TEACHER", "ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().defaultSuccessUrl("/profile")
                .permitAll()
                .and()
                .logout().permitAll().logoutSuccessUrl("/login")
                .and()
                .httpBasic();
    }

}
