package com.lin.config;


import com.lin.converter.DateConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * spring boot自定义配置类继承了WebMvcConfigurationSupport自动配置的静态资源路径就会失效
 * 因为项目类路径缺少 WebMvcConfigurationSupport类型的Bean时自动配置才会生效
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //并不能解决预请求跨域。
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("*")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //使用IDEA开发Spring Boot应用时，会导致一个问题，浏览器、编辑器不能同时访问JS等资源的问题。
        if (!registry.hasMappingForPattern("/static/**")){
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //添加自定义的字符串转日期convert
        registry.addConverter(new DateConverter());
    }
}
