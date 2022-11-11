package br.com.fiap.gs2.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthenticationConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                    // carros
                    .antMatchers(HttpMethod.GET, "/api/carro/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/carro").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/api/carro/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/carro/**").hasRole("ADMIN")

                    // user
                    .antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                    .antMatchers(HttpMethod.PUT, "/api/user/**").authenticated()
                    .antMatchers(HttpMethod.DELETE, "/api/user/**").authenticated()

                    // web
                    .antMatchers(HttpMethod.GET, "/carro/**").authenticated()
                    .antMatchers(HttpMethod.POST, "/carro/newCarro/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/user/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST,"/user/newUser/**").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .anyRequest().permitAll()
                .and()
                    .csrf().disable()
                // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .headers().frameOptions().disable()
                .and()
                .formLogin();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
