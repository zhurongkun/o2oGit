package cn.tarena.ht.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceRef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.service.ModuleService;
import cn.tarena.ht.service.RoleService;

@Controller
@RequestMapping("/sysadmin/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping("/list")
	public String findAll(Model model){
		
		List<Role> roleList = roleService.findAll();
		model.addAttribute("roleList", roleList);
		
		//转向角色列表页面
		return "/sysadmin/role/jRoleList";
	}
	
	
	@RequestMapping("/delete")
	public String toDelete(String[] roleId){
		
		roleService.deleteRoles(roleId);
		
		//转向角色列表页面
		return "redirect:/sysadmin/role/list";
	}
	
	
	//角色新增
	@RequestMapping("/tocreate")
	public String tocreate(){
		
		//跳转角色列表页面
		return "/sysadmin/role/jRoleCreate";
	}
	
	
	//角色新增
	@RequestMapping("/save")
	public String save(Role role){
		
		roleService.saveRole(role);
		
		//跳转列表页面
		return "redirect:/sysadmin/role/list";
		
	}
	
	
	@RequestMapping("toupdate")
	public String toupdate(String roleId,Model model){
		
		Role role = roleService.findOne(roleId);
		
		model.addAttribute("role", role);
		
		//转向角色修改页面
		return "/sysadmin/role/jRoleUpdate";
	}
	
	@RequestMapping("/update")
	public String updateRole(Role role){
		
		roleService.updateRole(role);
		
		return "redirect:/sysadmin/role/list";
	}
	
	@RequestMapping("/toview")
	public String toview(String roleId,Model model){
		
		Role role = roleService.findOne(roleId);
		
		model.addAttribute("role", role);
		
		//转向到角色查看页面
		return "/sysadmin/role/jRoleView";
	}
	
	@RequestMapping("/toModule")
	public String toModule(String roleId,Model model) throws JsonProcessingException{
		//角色所拥有的全部的模块id
		List<String> moduleIdList = roleService.findModuleIdListByRoleId(roleId);

		//查询全部的模块信息
		List<Module> moduleList = moduleService.findAll();
		
		for (Module module : moduleList) {
			//判断Id是否一致
			if(moduleIdList.contains(module.getModuleId())){
				module.setChecked(true);
			}
		}
		ObjectMapper objectMapper = new ObjectMapper();
		
		String zTreeJson = objectMapper.writeValueAsString(moduleList);
		model.addAttribute("zTreeJson", zTreeJson);
		model.addAttribute("roleId", roleId);
		return "/sysadmin/role/jRoleModule";
	}
	
	
	//角色模块的分配
	@RequestMapping("/saveRoleModule")
	public String saveRoleModule(String roleId,String[] moduleIds){
		
		roleService.saveRoleModule(roleId,moduleIds);
		
		return "redirect:/sysadmin/role/list";
		
	}
	
	
	
	
	
	
	
	
}
