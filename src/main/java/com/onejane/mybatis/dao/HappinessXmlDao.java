package com.onejane.mybatis.dao;

import com.onejane.mybatis.entity.Happiness;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface HappinessXmlDao {
    Happiness findHappinessByCity(String city);
    int insertHappiness(HashMap<String, Object> map);
}
