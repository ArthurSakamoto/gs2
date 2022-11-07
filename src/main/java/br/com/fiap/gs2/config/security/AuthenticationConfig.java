package br.com.fiap.gs2.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthenticationConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.httpBasic()
            .and()
            .authorizeRequests()
                // carros
                .antMatchers(HttpMethod.GET, "/api/carro/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/carro").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/carro/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/carro/**").permitAll()

                // user
                .antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/user/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/user/**").authenticated()

                // web
                .antMatchers("/carro/**").permitAll()
                .antMatchers("/carro/newCarro/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/user/newUser/**").permitAll()
                .antMatchers("/css/**").permitAll()

                .anyRequest().denyAll()
            .and()
                .csrf().disable()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .headers().frameOptions().disable()
            .and()
                .formLogin()
                .successForwardUrl("/carro")
        ;

        return http.build();
    }

}
