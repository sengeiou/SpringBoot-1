package com.onejane.poi.dao;

import com.onejane.poi.vo.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerDao {

    void addUser(Customer sysUser);

    int updateUserByName(Customer sysUser);

    int selectByName(String name);
}