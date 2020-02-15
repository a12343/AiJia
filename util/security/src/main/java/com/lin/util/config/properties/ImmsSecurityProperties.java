package com.lin.util.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Security配置类
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Component
@ConfigurationProperties(prefix = "imms.security")
@Data
public class ImmsSecurityProperties {

    /**
     * 是否开启验证
     */
    private boolean enableAuthentication = true;
}
