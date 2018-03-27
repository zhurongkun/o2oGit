package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.service.ModuleService;

@Controller
@RequestMapping("/sysadmin/module")
public class ModuleController {
	
	@Autowired
	private ModuleService moduleService;
	
	
	@RequestMapping("/list")
	public String findAll(Model model){
		
		List<Module> moduleList = moduleService.findAll();
		model.addAttribute("moduleList", moduleList);
		
		//实现页面跳转
		return "/sysadmin/module/jModuleList";
	}
	
	//状态的停用
	@RequestMapping("/stop")
	public String toStop(@RequestParam("moduleId")String[] moduleIds){
		int state = 0;
		
		moduleService.updateState(state,moduleIds);
		return "redirect:/sysadmin/module/list";
	}
	
	//状态的启用
	@RequestMapping("/start")
	public String toStart(@RequestParam("moduleId")String[] moduleIds){
		int state = 1;
		moduleService.updateState(state,moduleIds);
		return "redirect:/sysadmin/module/list";
	}
	
	@RequestMapping("/delete")
	public String toDelete(@RequestParam(value="moduleId",required=true)String[] moduleIds){
			
		moduleService.deleteModules(moduleIds);
		return "redirect:/sysadmin/module/list";
	}
	
	
	//模块的新增转向
	@RequestMapping("/tocreate")
	public String tocreate(Model model){
		
		//准备上级模块信息
		List<Module> pList = moduleService.findParentList();
		
		model.addAttribute("pList", pList);
		
		return "/sysadmin/module/jModuleCreate";
	}
	
	
	//模块的新增
	@RequestMapping("/save")
	public String saveModule(Module module){
		
		moduleService.saveModule(module);
		
		return "redirect:/sysadmin/module/list";
	}
	
	//模块查看
	@RequestMapping("/toview")
	public String toview(String moduleId,Model model){
		
		Module module = moduleService.findOne(moduleId);
		
		model.addAttribute("module", module);
		//转向模块查看页面
		return "/sysadmin/module/jModuleView";
	}
	
	
	/*@RequestMapping("/toupdate")
	public String updateModule(String moduleId){
		//注意隐藏域的问题
		 
	
	}*/
	
	
	
	
	
	
}
