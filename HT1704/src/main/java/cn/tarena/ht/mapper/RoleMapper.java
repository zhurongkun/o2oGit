package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.tarena.ht.pojo.Role;

public interface RoleMapper {
	
	//查询全部角色信息
	@Select("select * from role_p order by order_no")
	/*@Insert
	@Update
	@Delete*/
	public List<Role> findAll();
	public void deleteRoles(String[] roleId);
	
	public void saveRole(Role role);
	
	@Select("select * from role_p where role_id =#{roleId}")
	public Role findOne(String roleId);
	public void updateRole(Role role);
	
	@Select("select role_id from role_user_p where user_id =#{userId}")
	public List<String> findRoleIdByUserId(String userId);
	
	@Delete("delete from role_module_p where role_id = #{roleId}")
	public void deleteRoleModule(String roleId);
	
	@Insert("insert into role_module_p(module_id,role_id) values(#{moduleId},#{roleId})")
	public void saveRoleModule(@Param("roleId")String roleId,@Param("moduleId")String moduleId);
	
	public List<String> findModuleIdListByRoleId(String roleId);
	public void deleteRoleUser(String[] roleId);
	
	public void deleteRoleModules(String[] roleId);
}
