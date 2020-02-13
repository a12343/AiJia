package com.aijia.mybatis.support;

import com.aijia.constant.Constants;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;

/**
 * h
 * 默认租户处理 handler
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public class DefaultTenantHandler implements TenantHandler {

    /**
     * 租户 column
     */
    private final String TENANT_ID = "tenant_id";


    @Override
    public Expression getTenantId() {
        return new StringValue(Constants.ANONYMOUS_TENANT);
    }

    @Override
    public String getTenantIdColumn() {
        return TENANT_ID;
    }

    @Override
    public boolean doTableFilter(String tableName) {
        // 这里可以过滤哪些表不需要租户过滤
        return false;
    }
}
