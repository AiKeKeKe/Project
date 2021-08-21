package com.cy.pj.common.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security 的配置类,该类的三个注解分别是标识该类是配置类、开启 Security 服务、开启全局 Securtiy 注解。
 * 参考：https://blog.csdn.net/yuanlaijike?type=blog
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //替换掉默认的userDetailsService
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/doLoginUI")
                .defaultSuccessUrl("/doIndexUI").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .rememberMe();//两种实现方式1.Cookie存储2.数据库存储。当前使用cookie存储
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行静态资源
        web.ignoring().antMatchers("/static/**","/templates/**");
    }


}
