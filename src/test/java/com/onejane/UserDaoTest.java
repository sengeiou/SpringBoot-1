package com.onejane;

import com.onejane.jpa.dao.UserDao;
import com.onejane.jpa.entity.UserEntity;
import net.minidev.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {


    @Autowired
    private UserDao userDao;

    @Before
    public void before() {
        userDao.save(UserEntity.builder().id(5L).account("wentian").pwd("123456").name("向问天").build());
        userDao.save(UserEntity.builder().id(3L).account("bubai").pwd("123456").name("东方不败").build());
        userDao.save(UserEntity.builder().id(1L).account("7fengqy").pwd("1234567").name("7风清扬").build());
    }

    @Test
    public void testAdd() {
        userDao.save(UserEntity.builder().id(2L).account("renwox").pwd("123456").name("任我行").build());
        userDao.save(UserEntity.builder().id(4L).account("7linghuc").pwd("1234567").name("令狐冲").build());
    }


    @Test
    public void testLocate() {
        Example<UserEntity> example = Example.of(UserEntity.builder().id(2L).build());
        Optional<UserEntity> userEntityOptional = userDao.findOne(example);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            System.out.println("name = " + userEntity.getName());
            System.out.println("account = " + userEntity.getAccount());
        }

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pwd", ExampleMatcher.GenericPropertyMatchers.endsWith())//endsWith是categoryName 结尾为7的数据
                 .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withIgnorePaths("account");//name字段不参与匹配
        //创建实例
        Example<UserEntity> userEntityExample = Example.of(UserEntity.builder().pwd("7").build(),exampleMatcher);

        //查询
        List<UserEntity> one = userDao.findAll(userEntityExample);
        System.out.println(one);
    }

    @Test
    public void testFindAll() {
        List<UserEntity> userEntities = userDao.findAll(new Sort(Sort.Direction.DESC, "account"));
        for (UserEntity userEntity : userEntities) {
            System.out.println("name = " + userEntity.getName());
            System.out.println("account = " + userEntity.getAccount());
        }
    }

    @Test
    public void testFindByAccount() {
        UserEntity userEntity = userDao.findByAccount("wentian");
        if (userEntity != null) {
            System.out.println("name = " + userEntity.getName());
            System.out.println("account = " + userEntity.getAccount());
        }
    }

    @Test
    public void testFindTwoName() {
        List<UserEntity> twoName = userDao.findTwoName("风清扬", "东方不败");
        System.out.println(JSONArray.toJSONString(twoName));
    }


    @Test
    public void testFindUsersByRole() {
        List<UserEntity> userEntities = userDao.findUsersByRole(1L);
        System.out.println(JSONArray.toJSONString(userEntities));
    }


    @After
    public void after() {
        userDao.deleteById(1L);
        userDao.deleteById(3L);
        userDao.deleteById(5L);
    }
}