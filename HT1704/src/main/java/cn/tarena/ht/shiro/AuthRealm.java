package cn.tarena.ht.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.sun.org.apache.xerces.internal.xs.StringList;

import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.UserService;
import oracle.net.aso.s;

public class AuthRealm extends AuthorizingRealm{
	@Resource
	private UserService userService;
	
	//权限控制
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		//展现用户的真实的模块信息
		/*List<String> pList = new ArrayList<String>();
		pList.add("货运管理");
		pList.add("基础信息");
		pList.add("系统管理");*/
		
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		String userId = user.getUserId();
		
		//通过信息查询模块信息
		List<String> pList = userService.findModuleNameList(userId);
		
		//创建权限认证的对象
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		info.addStringPermissions(pList);
		
		return info;
	}
	
	
	
	
	
	//登陆认证   
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//通过realm 返回给安全中心 真实的用户信息
		
		//获取用户名
		UsernamePasswordToken loginToken = (UsernamePasswordToken) token;
		
		String username = loginToken.getUsername();
		
		//表示真实的信息
		User user = userService.findUserByUserName(username);
		
		/*
		 * principal: 真实的对象
		 * credentials: 表示真实的密码 
		 * realmName:  当前的realm
		 */
		
		AuthenticationInfo info = 
				new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		
		
		return info;
	}
	
	




	
	
	
}
