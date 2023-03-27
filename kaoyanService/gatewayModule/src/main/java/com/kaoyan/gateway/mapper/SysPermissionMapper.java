package com.kaoyan.gateway.mapper;

import com.kaoyan.gateway.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author cxn
 * @since 2023-03-04
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    List<String> selectPermsByUserId(int userId);
}
