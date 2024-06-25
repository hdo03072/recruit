package com.toy.recruit.core.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

//    @Bean
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return
                http
                        .csrf(AbstractHttpConfigurer::disable)
                        .cors(AbstractHttpConfigurer::disable)

                        .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                                .requestMatchers(("/admin/**")).authenticated()
                                .requestMatchers(("/api/**")).authenticated()
                                .anyRequest().permitAll())

                        .formLogin((formLogin) ->
                                formLogin.loginPage("/login"))

                        .logout((logoutConfig) ->
                                logoutConfig.logoutSuccessUrl("/"))

                        .exceptionHandling((exceptionConfig) -> exceptionConfig
                                .authenticationEntryPoint(new AjaxAuthenticationEntryPoint("/login"))
                                .accessDeniedHandler(customAccessDeniedHandler))

                        .authenticationProvider(authenticationProvider())
                        .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new AuthenticationProviderImpl(userDetailsServiceImpl, passwordEncoder());
    }

}
