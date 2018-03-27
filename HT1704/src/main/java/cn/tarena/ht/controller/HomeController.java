package cn.tarena.ht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
	
	//home.action
	
	//转向欢迎页面
	@RequestMapping("/home")
	public String home(){
		return "/home/fmain";
	}
	
	//转向tilte标题栏页面
	@RequestMapping("/title")
	public String title(){
		return "/home/title";
	}
	
	/*//转向home的左侧页面
	@RequestMapping("/homeLeft")
	public String homeLeft(){
		return "/home/left";
	}
	
	//转向home的操作页面
	@RequestMapping("/homeMain")
	public String homeMain(){
		return "/home/main";
	}*/
	//sysadminLeft.action      Left.actionMain.action  
	
	
	@RequestMapping("/{module}/Left")
	public String sysadminLeft(@PathVariable String module){
		return "/"+module+"/left";
	}
	
	@RequestMapping("/{module}/Main")
	public String sysadminMain(@PathVariable String module){
		return "/"+module+"/main";
	}
	
	
	
	//原有的策略

	/*@RequestMapping("/sysadminLeft")
	public String sysadminLeft(){
		return "/sysadmin/left";
	}
	
	@RequestMapping("/sysadminMain")
	public String sysadminMain(){
		return "/sysadmin/main";
	}*/
	
}
