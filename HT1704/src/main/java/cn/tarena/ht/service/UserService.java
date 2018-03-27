package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;

public interface UserService {
	public List<User> findAll();

	public void updateState(String[] userIds, int state);

	public void deleteUsers(String[] userIds);

	public List<UserInfo> findUserInfoList();

	public void saveUser(User user);
	
	//查询上级领导信息
	public List<UserInfo> findManagerList(String userId);

	public User findOne(String userId);

	public void updateUser(User user);

	public void saveRoleUser(String userId, String[] roleIds);

	public User findUserByU_P(String userName, String password);

	public User findUserByUserName(String username);

	public List<String> findModuleNameList(String userId);
}
