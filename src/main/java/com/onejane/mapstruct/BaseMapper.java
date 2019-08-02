package com.onejane.mapstruct;

import java.util.List;

public interface BaseMapper<ENTITY, VO> {
    /**
     * 实体类转VO
     */
    VO toVo(ENTITY entity);
    /**
     * 实体类列表转VO列表
     */
    List<VO> toVo(List<ENTITY> entities);
    /**
     * VO转实体类
     */
    ENTITY toEntity(VO vo);
    /**
     * VO列表转实体类列表
     */
    List<ENTITY> toEntity(List<VO> vos);
}