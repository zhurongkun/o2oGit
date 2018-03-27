package cn.tarena.ht.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.UserService;
import cn.tarena.ht.tool.Md5Password;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	//转向登陆页面
	@RequestMapping("/tologin")
	public String toLogin(){
		
		return "/sysadmin/login/login";
	}
	
	
	//系统的登出
	@RequestMapping("/logout")
	public String logout(HttpSession httpSession){
		
		//将用户的信息从session中删除
		httpSession.removeAttribute("sessionUser");
		//跳转到登陆页面
		return "/sysadmin/login/login";
	}
	
	
	//已Shiro的方式进行登陆
	@RequestMapping("/login")
	public String login(String userName,String password,Model model,HttpSession session){
		//1.判断用户名和密码是否为空
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
			//证明用户名或密码为空
			model.addAttribute("errorInfo", "用户名或密码不能为空");
			
			return "/sysadmin/login/login";
		}
		
		//用户名和密码不为空
		
		//Shiro的登陆操作   获取用户对象
		Subject subject = SecurityUtils.getSubject();
		
		//将用户的数据封装为令牌(票)
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		
		try {
			//通过用户实现登陆 
			subject.login(token); 
			
			//获取真实的用户对象
			User user = (User) subject.getPrincipal();
			
			//session.setAttribute("sessionUser", user);
			subject.getSession().setAttribute("sessionUser", user);
			//证明用户名和密码正确
			return "redirect:/home.action";
			
		} catch (AuthenticationException e) {
			e.printStackTrace();  //打印异常信息
			//证明用户名和密码错误
			model.addAttribute("errorInfo", "用户名和密码不正确");
			return "/sysadmin/login/login";
		}
	}
	
	/*@RequestMapping("/login")
	public String login(String userName,String password,Model model,HttpSession session){
		//1.判断用户名和密码是否为空
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
			//证明用户名或密码为空
			model.addAttribute("errorInfo", "用户名或密码不能为空");
			
			return "/sysadmin/login/login";
		}

		//2.根据用户名和密码查询数据
		
		//将密码进行加密
		password = Md5Password.getMd5HashPassword(password, userName);
		
		User user = userService.findUserByU_P(userName,password);
		if(user ==null){
			//用户名或密码不正确
			model.addAttribute("errorInfo", "用户名和密码不正确");
			return "/sysadmin/login/login";
		}
		
		//3.将用户数据存入session域中
		session.setAttribute("sessionUser", user);
		return "redirect:/home.action";
	}*/

}
