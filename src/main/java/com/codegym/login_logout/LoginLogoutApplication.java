package com.codegym.login_logout;

import com.codegym.login_logout.repository.UserRepository;
import com.codegym.login_logout.security.jwt.AuthTokenFilter;
import com.codegym.login_logout.services.User.UserServiceImp;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LoginLogoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginLogoutApplication.class, args);
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    @Qualifier("userDetailService")
    public UserDetailsService userDetailsServiceBean(UserRepository userRepository) {
        return new UserServiceImp(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
