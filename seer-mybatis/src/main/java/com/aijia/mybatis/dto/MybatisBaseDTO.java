package com.aijia.mybatis.dto;


import com.aijia.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * mybatis 基础 DTO
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MybatisBaseDTO extends BaseDTO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 328140509183690808L;

    /**
     * 乐观锁
     */
    private Integer version;
}
