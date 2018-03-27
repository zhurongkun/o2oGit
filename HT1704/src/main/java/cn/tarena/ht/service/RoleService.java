package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Role;

public interface RoleService {
	public List<Role> findAll();

	public void deleteRoles(String[] roleId);

	public void saveRole(Role role);

	public Role findOne(String roleId);

	public void updateRole(Role role);

	public List<String> findRoleIdByUserId(String userId);

	public void saveRoleModule(String roleId, String[] moduleIds);

	public List<String> findModuleIdListByRoleId(String roleId);
}
