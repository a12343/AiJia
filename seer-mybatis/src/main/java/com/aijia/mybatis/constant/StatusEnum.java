package com.aijia.mybatis.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 普通状态枚举
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public enum StatusEnum {

    /**
     * 正常.
     */
    NORMAL("0"),
    /**
     * 禁用.
     */
    DISABLE("1");

    /**
     * Code
     */
    @EnumValue
    private final String code;

    /**
     * Status enum.
     *
     * @param code the code
     */
    StatusEnum(String code) {
        this.code = code;
    }
}
