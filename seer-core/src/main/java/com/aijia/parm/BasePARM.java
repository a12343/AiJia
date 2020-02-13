package com.aijia.parm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 基础PARM
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePARM implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3048513438751770491L;

    /**
     * 唯一编号
     */
    private String id;
}
