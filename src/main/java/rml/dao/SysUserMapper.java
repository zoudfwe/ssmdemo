package rml.dao;

import java.util.List;

import rml.model.SysPermission;
import rml.model.SysRole;
import rml.model.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

	SysUser getSysUserByUsername(String username);

	List<SysRole> getSysRolesBySysUserId(String userId);

	List<SysPermission> getSysPermissionsBySysRoleId(String roleId);
}