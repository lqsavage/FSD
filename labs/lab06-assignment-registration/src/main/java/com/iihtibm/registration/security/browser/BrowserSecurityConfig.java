package com.iihtibm.registration.security.browser;

import com.iihtibm.registration.handler.MyAuthenticationFailureHandler;
import com.iihtibm.registration.handler.MyAuthenticationSucessHandler;
import com.iihtibm.registration.handler.MyLogoutHandler;
import com.iihtibm.registration.handler.MyLogoutSuccessHandler;
import com.iihtibm.registration.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author savagelee
 */
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSucessHandler authenticationSucessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) // Add validate code filter
                .formLogin() // Login form
                    // http.httpBasic() // HTTP Basic login
                    .loginPage("/login_2.html") // Redirect URL
                    .loginProcessingUrl("/login") // The login URL
                    .successHandler(authenticationSucessHandler) // Login successfully handler
                    .failureHandler(authenticationFailureHandler) // login failure handler
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/logoutSuccess")
                    .invalidateHttpSession(true).deleteCookies("cookiename")
                    .addLogoutHandler(new MyLogoutHandler()).logoutSuccessHandler(new MyLogoutSuccessHandler())
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository()) // Persistent token repository
                    .tokenValiditySeconds(3600) // Token expiration time(seconds)
                    .userDetailsService(userDetailService) // login process
                .and()
                    .authorizeRequests() // authorization configuration
                    .antMatchers("/authentication/require", "/login.html", "/login_2.html",
                            "/login", "/logout",
                            "/code/image", "/css/**", "/js/**", "/fonts/**", "/img/**", "/images/**",
                            "font-awesome/**", "LESS/**").permitAll() // Redirect URL, don't need to be authenticated
                    .anyRequest()  // All requests
                    .authenticated() // Should be authenticated
                .and()
                    .csrf().disable();
    }

}
