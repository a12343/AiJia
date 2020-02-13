package com.aijia.mybatis.config;

import com.aijia.mybatis.config.properties.SeerMybatisPlusProperties;
import com.aijia.mybatis.support.DefaultMetaObjectHandler;
import com.aijia.mybatis.support.DefaultTenantHandler;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * MybatisPlus 自动配置
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Configuration
@Slf4j
public class MybatisPlusAutoConfig {

    /**
     * 属性配置.
     *
     * @return the seer mybatis plus properties
     * @author : yangjunqing / 2019-04-15
     */
    @Bean
    @ConfigurationProperties(prefix = "seer.mybatis")
    public SeerMybatisPlusProperties seerMybatisPlusProperties(){
        return new SeerMybatisPlusProperties();
    }

    /**
     * 自动填充(审计).
     *
     * @return the meta object handler
     * @author : yangjunqing / 2019-04-15
     */
    @Bean
    @ConditionalOnMissingBean(MetaObjectHandler.class)
    public MetaObjectHandler metaObjectHandler(){
        log.info("MybatisPlus注册默认填充策略..");
        return new DefaultMetaObjectHandler();
    }

    /**
     * 乐观锁.
     *
     * @return the optimistic locker interceptor
     * @author : yangjunqing / 2019-04-15
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        log.info("MybatisPlus注册乐观锁拦截器..");
        return new OptimisticLockerInterceptor();
    }

    /**
     * 逻辑删除注入器.
     *
     * @return the sql injector
     * @author : yangjunqing / 2019-04-15
     */
    @Bean
    @ConditionalOnProperty(prefix = "seer.mybatis", name = "enable-logic-delete", havingValue = "true")
    public ISqlInjector sqlInjector(){
        log.info("MybatisPlus注册逻辑删除注入器..");
        return new LogicSqlInjector();
    }

    /**
     * 分页处理 handler.
     *
     * @return the tenant handler
     * @author : yangjunqing / 2019-04-15
     */
    @Bean
    @ConditionalOnProperty(prefix = "seer.mybatis", name = "enable-tenant", havingValue = "true")
    @ConditionalOnMissingBean(TenantHandler.class)
    public TenantHandler tenantHandler(){
        return new DefaultTenantHandler();
    }

    /**
     * 分页拦截器(不支持租户).
     *
     * @return the pagination interceptor
     * @author : yangjunqing / 2019-04-15
     */
    @Bean
    @ConditionalOnProperty(prefix = "seer.mybatis", name = "enable-tenant", havingValue = "false")
    public PaginationInterceptor paginationInterceptor(){
        log.info("MybatisPlus注册分页拦截器..");
        return new PaginationInterceptor();
    }

    /**
     * 分页拦截器(支持租户).
     *
     * @param tenantHandler the tenant handler
     * @return the tenant pagination interceptor
     * @author : yangjunqing / 2019-04-15
     */
    @Bean
    @ConditionalOnProperty(prefix = "seer.mybatis", name = "enable-tenant", havingValue = "true")
    public PaginationInterceptor tenantPaginationInterceptor(TenantHandler tenantHandler){
        log.info("MybatisPlus注册分页拦截器(支持租户)..");

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        List<ISqlParser> sqlParsers = Lists.newArrayList();
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(tenantHandler);
        sqlParsers.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParsers);

        return paginationInterceptor;
    }
}
