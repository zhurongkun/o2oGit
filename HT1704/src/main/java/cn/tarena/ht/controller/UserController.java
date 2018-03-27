package cn.tarena.ht.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.service.DeptService;
import cn.tarena.ht.service.RoleService;
import cn.tarena.ht.service.UserService;

@Controller
@RequestMapping("/sysadmin/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private RoleService roleService;
	
	
	//查询全部用户信息
	@RequestMapping("/list")
	public String findAll(Model model){
		List<User> userList = userService.findAll();
		
		model.addAttribute("userList", userList);
		
		//页面跳转
		return "/sysadmin/user/jUserList";
		
	}
	

	//状态的停用
	@RequestMapping("/stop")
	public String toStop(@RequestParam(value="userId",required=true)String[] userIds){
		
		int state = 0;
		userService.updateState(userIds,state);
		
		return "redirect:/sysadmin/user/list";
	}
	
	
	
	//状态的启用
	@RequestMapping("/start")
	public String toStart(@RequestParam(value="userId",required=true)String[] userIds){
		
		int state = 1;
		userService.updateState(userIds,state);
		
		return "redirect:/sysadmin/user/list";
	}
	
	//用户的删除
	@RequestMapping("/delete")
	public String toDelete(@RequestParam(value="userId",required=true)String[] userIds,HttpServletRequest request){
		/*String path = request.getContextPath(); //获取项目路径
		
		System.out.println("当前的项目路径:"+path+"~~~~~~~~~~~~~~~~~~~~");*/
		userService.deleteUsers(userIds);
		
		return "redirect:/sysadmin/user/list";
		
	}
	
	//用户新增
	@RequestMapping("/tocreate")
	public String tocreate(Model model){
		
		//查询部门信息
		List<Dept>  deptList = deptService.findParent();
		
		//查询上级领导信息
		List<UserInfo> userInfoList = userService.findUserInfoList();
		
		model.addAttribute("deptList", deptList);
		model.addAttribute("userInfoList", userInfoList);
		
		return "/sysadmin/user/jUserCreate";
	}
	
	//新增用户
	@RequestMapping("/save")
	public String saveUser(User user){
		
		userService.saveUser(user);
		
		return "redirect:/sysadmin/user/list";
	}
	
	
	//用户修改转向
	@RequestMapping("/toupdate")
	public String toUpdate(String userId,Model model){
		
		//准备部门列表信息
		List<Dept> deptList = deptService.findParent();
		
		//准备上级领导信息  去除自己之外
		List<UserInfo> infoList = userService.findManagerList(userId);
		
		//根据userId查询信息
		User userMsg = userService.findOne(userId);
		
		UserInfo info = userMsg.getUserInfo();
		model.addAttribute("deptList", deptList);
		model.addAttribute("infoList", infoList);
		model.addAttribute("userMsg", userMsg);
		model.addAttribute("info", info);
		
		return "/sysadmin/user/jUserUpdate";
	}
	
	
	@RequestMapping("/update")
	public String updateUser(User user){
		
		userService.updateUser(user);
		
		
		return "redirect:/sysadmin/user/list";
	}
	
	
	
	//转想到角色分配页面
	@RequestMapping("/role")
	public String toRole(String userId,Model model) throws JsonProcessingException{
		
		//根据UserId查询角色Id信息
		List<String> roleIdList =  roleService.findRoleIdByUserId(userId);
		
		//查询全部角色信息
		List<Role> roleList = roleService.findAll();
		
		for (Role role : roleList) {
			
			if(roleIdList.contains(role.getRoleId())){
				//当前的id是用户已有的角色信息
				role.setChecked(true);
			}
		}
		
		
		//将roleList转化为JSON串
		ObjectMapper objectMapper = new ObjectMapper();
		String zTreeJson = objectMapper.writeValueAsString(roleList);
		
		model.addAttribute("zTreeJson", zTreeJson);
		
		//将UserId存入域中
		model.addAttribute("userId", userId);
		
		return "/sysadmin/user/jUserRole";
	}
	
	
	@RequestMapping("/saveUserRole")
	public String saveRole(String userId,String[] roleIds){
		
		//将用户和角色信息进行入库操作
		userService.saveRoleUser(userId,roleIds);
		
		return "redirect:/sysadmin/user/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
