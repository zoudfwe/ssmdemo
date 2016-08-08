package rml.service;

import java.util.List;

import rml.model.SysPermission;
import rml.model.SysRole;
import rml.model.SysUser;

public interface SysUserServiceI {

	SysUser getSysUserByUsername(String currentUsername);

	List<SysRole> getSysRolesBySysUserId(String id);

	List<SysPermission> getSysPermissionsBySysRoleId(String id);

	SysUser getSysUserById(String id);
}
