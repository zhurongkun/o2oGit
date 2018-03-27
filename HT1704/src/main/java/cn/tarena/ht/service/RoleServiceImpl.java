package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.pojo.Role;
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Role> findAll() {
		
		return roleMapper.findAll();
	}

	@Override
	public void deleteRoles(String[] roleId) {
		
		//关联删除role_user_p,role_module_p
		roleMapper.deleteRoleUser(roleId);
		roleMapper.deleteRoleModules(roleId);
		roleMapper.deleteRoles(roleId);
	}

	@Override
	public void saveRole(Role role) {
		//准备数据
		role.setRoleId(UUID.randomUUID().toString());
		role.setCreateTime(new Date());
		
		roleMapper.saveRole(role);
		
	}

	@Override
	public Role findOne(String roleId) {
		
		return roleMapper.findOne(roleId);
	}

	@Override
	public void updateRole(Role role) {
		
		role.setUpdateTime(new Date());
		roleMapper.updateRole(role);
		
	}

	@Override
	public List<String> findRoleIdByUserId(String userId) {
		
		return roleMapper.findRoleIdByUserId(userId);
	}

	@Override
	public void saveRoleModule(String roleId, String[] moduleIds) {
		
		//为了防止重复提交,先删除 再插入
		roleMapper.deleteRoleModule(roleId);
		
		
		//再次进行插入操作
		for (String moduleId : moduleIds) {
			roleMapper.saveRoleModule(roleId,moduleId);
		}
		
	}

	@Override
	public List<String> findModuleIdListByRoleId(String roleId) {
		
		return roleMapper.findModuleIdListByRoleId(roleId);
	}

}
