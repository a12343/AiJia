package com.aijia.mybatis.parm;


import com.aijia.parm.BasePARM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * mybatis 基础 PARM
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MybatisBasePARM extends BasePARM {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7618458013993569059L;

    /**
     * 乐观锁
     */
    private Integer version;
}
