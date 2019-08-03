package com.onejane.multisource.service;

import com.onejane.multisource.entity.TApply;
import com.onejane.multisource.mapper.basemapper.TApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: codewj
 * @Date: 2019/8/3 16:56
 * @Description:
 */
@Service
public class TApplyService {
    @Autowired
    TApplyMapper tApplyMapper;

    public List<TApply> getAll() {
        return tApplyMapper.selectAll();
    }
}
