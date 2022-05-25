package com.example.proyecto.config;

import com.example.proyecto.jwt.JWTAuthentificationFilter;
import com.example.proyecto.jwt.JWTAuthorizationFilter;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter{

    private UserDetailsService userDetailsService;
  
    @Override
    protected void configure(HttpSecurity http) throws Exception {

      http
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
          .cors().and()
          .headers().frameOptions().disable().and()
          .csrf().disable()
          .authorizeRequests().antMatchers("/login", "/Dulce/**","/h2/**","/Cliente/Buscar/email", "/Cliente/Ingresar")
          .permitAll()
          .anyRequest().authenticated()
          .and()
          .addFilter(new JWTAuthentificationFilter(authenticationManager()))
          .addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService);
    }
    
}
