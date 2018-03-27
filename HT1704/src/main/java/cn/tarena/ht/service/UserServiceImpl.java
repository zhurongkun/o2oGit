package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.UserInfoMapper;
import cn.tarena.ht.mapper.UserMapper;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.tool.Md5Password;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public List<User> findAll() {
		
		return userMapper.findAll();
	}

	@Override
	public void updateState(String[] userIds, int state) {
		
		userMapper.updateState(userIds,state);
		
	}

	@Override
	public void deleteUsers(String[] userIds) {
		
		/*
		 * 维护关联关系 需要删除userInfo表和 user_role_p表
		 */
		userInfoMapper.deleteUserInfo(userIds);
		userMapper.deleteUserRoles(userIds);
		userMapper.deleteUsers(userIds);	
	}

	@Override
	public List<UserInfo> findUserInfoList() {
		
		return userInfoMapper.findUserInfoList();
	}

	@Override
	public void saveUser(User user) {
		//应该将user表和userinfo表同时入库,如果中间有异常 同时回滚
		
		String id = UUID.randomUUID().toString(); //生成UUID充当主键信息
		Date date = new Date();
		UserInfo info = user.getUserInfo();
		info.setCreateTime(date);
		info.setUserInfoId(id);
		
		user.setCreateTime(date);
		user.setUserId(id);
		
		//将密码进行加密
		String password = Md5Password.getMd5HashPassword(user.getPassword(), user.getUsername());
		user.setPassword(password);
		
		userMapper.saveUser(user);
		userInfoMapper.saveUserInfo(info);
		
	}

	@Override
	public List<UserInfo> findManagerList(String userId) {
		
		return userInfoMapper.findManagerList(userId);
	}

	@Override
	public User findOne(String userId) {
		
		return userMapper.findOne(userId);
	}

	@Override
	public void updateUser(User user) {
		//准备数据
		Date date = new Date();
		user.setUpdateTime(date);
		
		UserInfo info = user.getUserInfo();
		
		info.setUserInfoId(user.getUserId());
		info.setUpdateTime(date);
		
		
		userMapper.updateUser(user);
		//userInfoMapper.updateUser()  自己完成
		
	}

	@Override
	public void saveRoleUser(String userId, String[] roleIds) {
		
		//防止重复提交 先删除
		userMapper.deleteRoleUserByUserId(userId);
		
		
		//循环遍历 实现插入操作
		for (String roleId : roleIds) {
			userMapper.saveRoleUser(userId,roleId);
		}
		//userMapper.saveRoleUser(userId, roleIds);
		
	}

	@Override
	public User findUserByU_P(String userName, String password) {
		
		return userMapper.findUserByU_P(userName,password);
	}

	@Override
	public User findUserByUserName(String username) {
		
		return userMapper.findUserByUserName(username);
	}

	@Override
	public List<String> findModuleNameList(String userId) {
		
		return userMapper.findModuleNameList(userId);
	}

}
