package com.aijia.config;


import com.aijia.config.properties.SeerJpaProperties;
import com.aijia.support.DefaultAuditorAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Jpa 自动配置
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Configuration
@EnableJpaAuditing
public class JpaAutoConfig {

    /**
     * 属性配置.
     *
     * @return the seer jpa properties
     * @author : yangjunqing / 2019-04-16
     */
    @Bean
    @ConfigurationProperties(prefix = "seer.jpa")
    public SeerJpaProperties seerJpaProperties(){
        return new SeerJpaProperties();
    }

    /**
     * Jpa审计.
     *
     * @return the auditor aware
     * @author : yangjunqing / 2019-04-16
     */
    @Bean
    @ConditionalOnMissingBean(AuditorAware.class)
    public AuditorAware<String> auditorAware(){
        return new DefaultAuditorAware();
    }
}
