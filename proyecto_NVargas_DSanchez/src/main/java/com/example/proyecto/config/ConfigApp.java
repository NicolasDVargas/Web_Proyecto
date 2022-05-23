package com.example.proyecto.config;

import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ConfigApp {
    @Autowired
    private Environment env;

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(getListProperty("cors.allowed.origins"));
    config.setAllowedMethods(getListProperty("cors.allowed.methods"));
    config.setAllowedHeaders(getListProperty("cors.allowed.headers"));
    config.setExposedHeaders(getListProperty("cors.exposed.headers"));
    config.setAllowCredentials(getBooleanProperty("cors.allow.credentials"));
    config.setMaxAge(getLongProperty("cors.maxage"));
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

  private List<String> getListProperty(String key) {
    return Arrays.asList(env.getProperty(key).split(","));
  }

  private Boolean getBooleanProperty(String key) {
    return Boolean.valueOf(env.getProperty(key));
  }

  private Long getLongProperty(String key) {
    return Long.valueOf(env.getProperty(key));
  }
}
