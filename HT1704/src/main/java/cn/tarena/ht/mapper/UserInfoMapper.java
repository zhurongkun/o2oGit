package cn.tarena.ht.mapper;

import java.util.List;

import cn.tarena.ht.pojo.UserInfo;

public interface UserInfoMapper {
	public List<UserInfo> findUserInfoList();

	public void saveUserInfo(UserInfo info);

	public List<UserInfo> findManagerList(String userId);

	public void deleteUserInfo(String[] userIds);
}
