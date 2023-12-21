package dev.thapak.lovecalculator.lovecalculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import dev.thapak.lovecalculator.lovecalculator.repository.UserRepository;
import dev.thapak.lovecalculator.lovecalculator.service.impl.UserDetailsManagerImpl;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1. PasswordEncoder bean
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. UserDetailsService bean
    @Bean
    UserDetailsService userDetailsService() {
      /*   return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user don't found")); */

                return new UserDetailsManagerImpl(userRepository, passwordEncoder());
    }

    // 3. AuthenticationProvider bean
    @Bean
    AuthenticationProvider authenticationProvider() {
        // using DaoAuthenticationProvider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // set UserDetailsService to provider
        provider.setUserDetailsService(userDetailsService());
        // set PasswordEncoder to provider
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // 4. SecurityFilterChain bean
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf
                        .disable())
                .authorizeHttpRequests(authRequest -> authRequest

                        .requestMatchers("/signup", "/signupsucess").permitAll()
                        .requestMatchers("/","/contact","/processcontact").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/user").permitAll()
                        .requestMatchers("/css/**").permitAll()   // permit all css files into the static resouce folder
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/processlogin")
                        .usernameParameter("useremail")
                        .passwordParameter("userpassword")
                        .defaultSuccessUrl("/home")
                        .permitAll())
                .logout(logout -> logout
                        .permitAll())

                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                        .permitAll());

        return http.build();
    }

}
