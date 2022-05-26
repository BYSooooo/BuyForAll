package com.example.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailImpl userDetailImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        //권한 설정
        http.authorizeRequests()
        .antMatchers("/", "/main" ,"/css/**", "/templates").permitAll();
        //로그인 폼 관련 Security 설정
        http.formLogin()
        .loginPage("/loginForm")
        .successForwardUrl("/loginSuccess")
        .failureForwardUrl("/loginForm");

        //로그아웃 관련 Security 설정
        http.logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/main");


        //인증된 회원 정보를 사용하기 위해 UserDetails를 사용
        http.
        userDetailsService((UserDetailsService)userDetailImpl);
        
   
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        .antMatchers("/css/**","/images/**","/javascript/**");
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}