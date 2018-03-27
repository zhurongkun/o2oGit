package cn.tarena.ht.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import cn.tarena.ht.tool.Md5Password;
//Shiro内部加密算法
public class AuthCredential extends  SimpleCredentialsMatcher{
	
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken loginToken = (UsernamePasswordToken) token;
		
		//将密码进行加密
		String username = loginToken.getUsername();
		String password = String.valueOf(loginToken.getPassword());

		//经过加密的密文
		String md5Password = Md5Password.getMd5HashPassword(password, username);
		
		loginToken.setPassword(md5Password.toCharArray());
		
		return super.doCredentialsMatch(loginToken, info);
	}
}
