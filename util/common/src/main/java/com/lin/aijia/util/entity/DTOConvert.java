package com.lin.aijia.util.entity;

import java.util.List;

/**
 * DTO与实体类相互转换
 * @param <E> the type parameter
 * @param <T> the type parameter
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface DTOConvert<E,T> {

    /**
     * To dto t.
     *
     * @param e the e
     * @return the t
     * @author : yangjunqing / 2019-01-07
     */
    T toDTO(E e);

    /**
     * To entity e.
     *
     * @param t the t
     * @return the e
     * @author : yangjunqing / 2019-01-07
     */
    E toEntity(T t);

    /**
     * To dtos list.
     *
     * @param eList the e list
     * @return the list
     * @author : yangjunqing / 2019-01-07
     *
     */
    List<T> toDTOs(List<E> eList);

    /**
     * To entitys list.
     *
     * @param tList the t list
     * @return the list
     * @author : yangjunqing / 2019-01-07
     */
    List<E> toEntitys(List<T> tList);
}
