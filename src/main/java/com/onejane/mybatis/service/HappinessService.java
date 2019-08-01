package com.onejane.mybatis.service;

import com.onejane.mybatis.dao.HappinessDao;
import com.onejane.mybatis.entity.Happiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HappinessService {
    @Autowired
    private HappinessDao happinessDao;

    public Happiness selectService(String city){
        return happinessDao.findHappinessByCity(city);
    }

    @Transactional
    public void insertService(){
        happinessDao.insertHappiness("西安", 9421);
//        int a = 1 / 0; //模拟故障
        happinessDao.insertHappiness("长安", 1294);
    }
}