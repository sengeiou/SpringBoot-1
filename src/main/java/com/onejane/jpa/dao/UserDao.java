package com.onejane.jpa.dao;

import com.onejane.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 用户服务数据接口类
 *
 * 使用@Mapper替代@respository
 */

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {
    UserEntity findByAccount(String account);
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