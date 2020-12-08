package com.gytech.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.gytech.Configuration.CorsFilter;
import com.gytech.Utils.MD5Util;

/**
 * Created by LQ on 2018/9/7.
 * Security
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 不拦截的资源
     */
    public static final String[] PERMITTED_RESOURCES = { "/js/**","/css/**","/images/**",
            "/**/api","/error/**","/rest/**","/**/SowWs"};


    @Autowired
    private CustomUserService customUserService;

    @Autowired
    SessionRegistry sessionRegistry;

    @Autowired
    private CustomLoginFailureHandler customLoginFailureHandler;

    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(PERMITTED_RESOURCES);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService).passwordEncoder(new PasswordEncoder(){

            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String)rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String)rawPassword));
            }}); //user Details Service验证
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        		.antMatchers(PERMITTED_RESOURCES).permitAll()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/login/do").failureHandler(customLoginFailureHandler).successHandler(customLoginSuccessHandler)
                .permitAll() //登录页面用户任意访问
                .and()
                .logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/login").permitAll(); //注销行为任意访问
        http.sessionManagement()
                .sessionFixation().none()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER).maximumSessions(3)
                .maxSessionsPreventsLogin(false).sessionRegistry(sessionRegistry)
                .expiredUrl("/login");
    }

    @Bean
    public ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }
}
