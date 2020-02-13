package com.aijia.parm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA 基础 PARM
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JpaBasePARM extends BasePARM {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7727525267149743107L;

    /**
     * 版本
     */
    private Long version;
}
