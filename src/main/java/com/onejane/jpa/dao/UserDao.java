package com.onejane.jpa.dao;

import com.onejane.jpa.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * 用户服务数据接口类
 *
 * 使用@Mapper替代@respository
 */

@Mapper
@CacheConfig(cacheNames = "user")
public interface UserDao extends JpaRepository<UserEntity, Long> {

    @Cacheable(key ="#p0")
    UserEntity findByAccount(String account);

    // 如果指定为 true，则方法调用后将立即清空所有缓存
    @CacheEvict(key ="#p0",allEntries=true)
    void deleteByAccount(String account);

    @CachePut(key = "#p0")
    @Modifying(clearAutomatically = true)
    @Query(value = "update auth_user p set p.Name =?1 where p.account = ?2",nativeQuery = true)
    int updateByAccount( String name,  String account);


    UserEntity findByAccountAndPwd(String account, String pwd);
    List<UserEntity> findAllByIdGreaterThan(Long id);

    /**
     * pql语句
     * @param name1
     * @param name2
     * @return
     */
    @Query("SELECT O FROM UserEntity O WHERE O.name = :name1  OR O.name = :name2 ")
    List<UserEntity> findTwoName(@Param("name1") String name1, @Param("name2") String name2);

    /**
     * sql语句
     * @param name1
     * @param name2
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM AUTH_USER WHERE name = :name1  OR name = :name2 ")
    List<UserEntity> findSQL(@Param("name1") String name1, @Param("name2") String name2);

    /**
     * 根据用户角色id查询用户
     * @param roleId
     * @return
     */
    @Query("SELECT U FROM UserEntity U ,RoleUserEntity RU WHERE U.id = RU.userId AND RU.roleId = :roleId")
    // @Query("SELECT U.* FROM AUTH_USER U ,AUTH_ROLE_USER RU WHERE U.id = RU.user_id AND RU.role_id = :roleId")
    List<UserEntity> findUsersByRole(@Param("roleId") Long roleId);


}