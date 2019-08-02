package com.onejane.mapstruct;

import com.onejane.jpa.entity.RoleEntity;
import com.onejane.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapperRole extends BaseMapper<UserEntity, UserRoleVo> {
    UserMapperRole MAPPER = Mappers.getMapper(UserMapperRole.class);

    @Mappings({    // 多个组合
            @Mapping( target = "userName", source = "user.name" ),
            @Mapping( target = "roleName", source = "role.name" ),
            @Mapping( target = "id", source = "user.id" )
    })
    UserRoleVo toVo2(UserEntity user, RoleEntity role );

    List<UserRoleVo> toVos(List<UserEntity> list);
}