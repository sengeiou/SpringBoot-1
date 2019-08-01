package com.onejane.mybatis.service;

import com.onejane.mybatis.dao.HappinessXmlDao;
import com.onejane.mybatis.entity.Happiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;

@Service
public class HappinessXmlService {
    @Autowired
    private HappinessXmlDao happinessXmlDao;

    public Happiness selectService(String city){
        return happinessXmlDao.findHappinessByCity(city);
    }

    @Transactional
    public void insertService(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("city", "西安");
        map.put("num", 9421);

        happinessXmlDao.insertHappiness(map);
        int a = 1 / 0; //模拟故障
        happinessXmlDao.insertHappiness(map);
    }
}