package com.example.shopinglist1.secutiry;

import com.example.shopinglist1.secutiry.jwt.AuthEntryPointJwt;
import com.example.shopinglist1.secutiry.jwt.AuthTokenFilter;
import com.example.shopinglist1.secutiry.services.UserDetailsServiceImpl;
import com.example.shopinglist1.user.User.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

     @Autowired
     AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private static final String ADMIN = Role.ADMIN.toString();
    private static final String USER = Role.USER.toString();

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/visit/**").hasAnyAuthority(ADMIN, USER)
//                .antMatchers(HttpMethod.PATCH, "/api/visit/**").hasAnyAuthority(ADMIN, USER)
//                .antMatchers(HttpMethod.PUT, "/api/visit/**").hasAnyAuthority(ADMIN, USER)
//                .antMatchers(HttpMethod.DELETE, "/api/visit/**").hasAnyAuthority(ADMIN, USER)
//                .antMatchers(HttpMethod.POST, "/api/visit").hasAnyAuthority(ADMIN, USER)
//                .antMatchers(HttpMethod.GET, "/api/clinic/**").hasAnyAuthority(ADMIN, USER)
//                .antMatchers(HttpMethod.GET, "/api/patient/**").hasAnyAuthority(ADMIN, USER)
                .antMatchers(HttpMethod.GET, "/api/shoplist/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/shoplist/**").hasRole("USER")
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/**").permitAll().anyRequest().authenticated();
        http.authenticationProvider(authenticationProvider(userDetailsService));
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
