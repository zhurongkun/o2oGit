package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.User;

public interface UserMapper {
	public List<User> findAll();

	public void updateState(@Param("userIds")String[] userIds,@Param("state")int state);

	public void deleteUsers(String[] userIds);
	
	//新增用户
	public void saveUser(User user);

	public User findOne(String userId);

	public void updateUser(User user);
	
	@Insert("insert into role_user_p(role_id,user_id) values(#{roleId},#{userId})")
	public void saveRoleUser(@Param("userId")String userId,@Param("roleId")String roleId);
	
	@Delete("delete from role_user_p where user_id = #{userId}")
	public void deleteRoleUserByUserId(String userId);

	public void deleteUserRoles(String[] userIds);

	public User findUserByU_P(@Param("userName")String userName,@Param("password")String password);

	public User findUserByUserName(String username);

	public List<String> findModuleNameList(String userId);
	
	
	//批量插入Oracle
	//public void saveRoleUser(@Param("userId")String userId,@Param("roleIds")String[] roleIds);
	
	
	
	
	
}
