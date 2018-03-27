package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.Dept;

public interface DeptMapper {
	//查询全部部门信息
	public List<Dept> findAll();
	
	//修改状态  通过注解封装为Map
	public void updateState(@Param("deptIds")String[] deptIds, @Param("state")int state);
	
	//批量删除
	public void deleteDepts(String[] deptId);
	
	//查询部门信息
	public List<Dept> findParent();
	
	//新增部门
	public void saveDept(Dept dept);
	
	//部门的查看
	public Dept findOne(String deptId);
	
	//更新部门
	public void updateDept(Dept dept);
}
