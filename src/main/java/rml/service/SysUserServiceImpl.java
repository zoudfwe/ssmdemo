package rml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.SysUserMapper;
import rml.model.SysPermission;
import rml.model.SysRole;
import rml.model.SysUser;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserServiceI{

	private SysUserMapper sysUserMapper;
		
	public SysUserMapper getSysUserMapper() {
		return sysUserMapper;
	}
	
	@Autowired
	public void setSysUserMapper(SysUserMapper sysUserMapper) {
		this.sysUserMapper = sysUserMapper;
	}


	@Override
	public SysUser getSysUserByUsername(String currentUsername) {
		return sysUserMapper.getSysUserByUsername(currentUsername);
	}

	@Override
	public List<SysRole> getSysRolesBySysUserId(String userId) {
		return sysUserMapper.getSysRolesBySysUserId(userId);
	}

	@Override
	public List<SysPermission> getSysPermissionsBySysRoleId(String roleId) {
		return sysUserMapper.getSysPermissionsBySysRoleId(roleId);
	}

	@Override
	public SysUser getSysUserById(String id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

}
