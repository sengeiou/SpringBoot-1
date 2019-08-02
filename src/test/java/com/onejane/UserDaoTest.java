package com.onejane;

import com.onejane.jpa.dao.UserDao;
import com.onejane.jpa.entity.RoleEntity;
import com.onejane.jpa.entity.UserEntity;
import com.onejane.mapstruct.UserMapperRole;
import com.onejane.mapstruct.UserRoleVo;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
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

    @Test
    public void testdeleteByAccount(){
        userDao.deleteByAccount("renwox");
    }

    @Test
    public void updateByAccount(){
        userDao.updateByAccount("wangjian","renwox");
    }

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Test
    public void testRedisSet() {
        this.redisTemplate.opsForValue().set("study", "java");
        System.out.println(this.redisTemplate.opsForValue().get("study"));
    }


    @Resource
    private MongoTemplate mongoTemplate;
    @Test
    public void mongoSaveUser() {
        UserEntity user = new UserEntity();
        user.setId(2L);
        user.setAccount("codewj");
        user.setName("王建");
        user.setPwd("123");
        mongoTemplate.insert(user, "imooc");
    }

    @Test
    public void testMapStruct(){
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setAccount("codewj");
        user1.setName("wj");
        user1.setPwd("123");
        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        user2.setAccount("ashercode");
        user2.setName("wz");
        user2.setPwd("456");
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(user1);
        userEntities.add(user2);
        List<UserRoleVo> personVos = UserMapperRole.MAPPER.toVos(userEntities);
        log.info(personVos.toString());


        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(3L);
        roleEntity.setName("管理员");
        roleEntity.setNote("666");
        UserRoleVo userRoleVo = UserMapperRole.MAPPER.toVo2(user1, roleEntity);
        log.info(userRoleVo.toString());

        UserRoleVo userRoleVo1 = UserMapperRole.MAPPER.toVo(user1);
        log.info(userRoleVo1.toString());

    }
}
