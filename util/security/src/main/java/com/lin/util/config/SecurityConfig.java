package com.lin.util.config;


import com.lin.aijia.util.utils.JwtTokenUtils;
import com.lin.util.config.properties.ImmsSecurityProperties;
import com.lin.util.entity.JwtAccessDeniedHandler;
import com.lin.util.entity.JwtAuthenticationEntryPoint;
import com.lin.util.filter.JwtAuthorizationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security 配置
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImmsSecurityProperties immsSecurityProperties;

    @Autowired
    @Qualifier("jwtUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;


    @Bean
    public JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter(){
        return new JwtAuthorizationTokenFilter(userDetailsService, jwtTokenUtils);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers("/", "/static/**", "/favicon.ico", "/index.html", "/css/**", "/fonts/**", "/images/**", "/js/**", "/less/**")
                //忽略webjars资源目录
                .antMatchers("/webjars/**")
                //swagger需要忽略的路径
                .antMatchers("/swagger-resources/**", "/v2/api-docs/**", "/v2/api-docs-ext/**", "/doc.html")
                //文件预览/下载
                .antMatchers("/fileInfo/preview/**", "/fileInfo/download/**", "/fileInfo/headPicture/**")
                //Excel导出
                .antMatchers("/sparePartExpend/exportDetail/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //imms是否开启验证机制
        if (immsSecurityProperties.isEnableAuthentication()){
            System.out.println("是否开启验证"+immsSecurityProperties.isEnableAuthentication());
            http
                //不需要创建session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers("/user/login", "/user/logout").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .headers()
                    .cacheControl();
        }
        else {
            http
                .authorizeRequests()
                    .antMatchers("/**").permitAll();
        }

        //token方式不支持CSRF防护
        http.csrf().disable();
        http.addFilterBefore(jwtAuthorizationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler);
    }
}
