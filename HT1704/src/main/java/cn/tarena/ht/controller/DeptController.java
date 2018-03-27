package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.service.DeptService;

@Controller
@RequestMapping("/sysadmin/dept")
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	//请求展现部门列表页面
	@RequestMapping("/list")
	public String findAll(Model model){
		List<Dept> deptList = deptService.findAll();
		model.addAttribute("deptList", deptList);
		
		//返回页面  /sysadmin/dept/jDeptList
		return "/sysadmin/dept/jDeptList";
	}
	
	//修改状态  状态停用
	@RequestMapping("/stop")
	public String toStop(@RequestParam("deptId")String[] deptIds){
		//状态的修改 state=0
		int state = 0;  //停用
		
		deptService.updateState(deptIds,state);
		
		return "redirect:/sysadmin/dept/list";
	}
	
	
	//状态的启用
	@RequestMapping("/start")
	public String toStart(@RequestParam("deptId")String[] deptIds){
		//状态的修改 state=0
		int state = 1;  //停用
		
		deptService.updateState(deptIds,state);
		
		return "redirect:/sysadmin/dept/list";
	}
	
	
	//批量删除
	@RequestMapping("/delete")
	public String toDelete(String[] deptId){
		
		deptService.deleteDepts(deptId);
		
		//重定向回部门列表页面
		return "redirect:/sysadmin/dept/list";
	}
	
	
	//跳转部门新增页面
	@RequestMapping("/tocreate")
	public String toCreate(Model model){
		
		//准备上级部门数据
		List<Dept> parentList = deptService.findParent();
		
		model.addAttribute("parentList", parentList);
		
		return "/sysadmin/dept/jDeptCreate";
	}
	
	
	//部门的新增
	@RequestMapping("/save")
	public String saveDept(Dept dept){
		
		deptService.saveDept(dept);
		
		//跳转到列表页面
		return "redirect:/sysadmin/dept/list";
	}
	
	
	//部门的查看页面
	@RequestMapping("/toview")
	public String toView(String deptId,Model model){
		//根据部门Id查询数据
		Dept dept = deptService.findOne(deptId);
		model.addAttribute("dept", dept);
		
		//应该跳转到部门的查看页面
		return "/sysadmin/dept/jDeptView";
	}
	
	//部门的修改
	@RequestMapping("/toupdate")
	public String toUpdate(String deptId,Model model){
		
		Dept dept = deptService.findOne(deptId);
		
		//准备上级部门
		List<Dept> parentList = deptService.findParent();
		
		model.addAttribute("dept", dept);
		model.addAttribute("parentList", parentList);
		
		return "/sysadmin/dept/jDeptUpdate";
	}
	
	
	//部门更新
	@RequestMapping("/update")
	public String UpdateDept(Dept dept){
		
		deptService.updateDept(dept);
		
		return "redirect:/sysadmin/dept/list";
	}
	
	
	
	
	
	
	
	
	
	
}
