package com.onejane.multisource.service;

import com.onejane.multisource.entity.WebDic;
import com.onejane.multisource.mapper.dicmapper.WebDicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: codewj
 * @Date: 2019/7/25 12:52
 * @Description:
 */
@Service
public class WebDicService {
    @Autowired
    WebDicMapper webDicMapper;

    public List<WebDic> getAll() {
        return webDicMapper.selectAll();
    }
}
