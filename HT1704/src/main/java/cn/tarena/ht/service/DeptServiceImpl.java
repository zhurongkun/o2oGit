package cn.tarena.ht.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.DeptMapper;
import cn.tarena.ht.pojo.Dept;
@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public List<Dept> findAll() {
		
		return deptMapper.findAll();
	}

	@Override
	public void updateState(String[] deptIds, int state) {
		
		deptMapper.updateState(deptIds,state);
	}

	@Override
	public void deleteDepts(String[] deptId) {
		
		deptMapper.deleteDepts(deptId);
		
	}
	
	//查询部门信息
	@Override
	public List<Dept> findParent() {
		
		return deptMapper.findParent();
	}

	@Override
	public void saveDept(Dept dept) {
		
		deptMapper.saveDept(dept);
		
	}

	@Override
	public Dept findOne(String deptId) {
		
		return deptMapper.findOne(deptId);
	}

	@Override
	public void updateDept(Dept dept) {
		
		deptMapper.updateDept(dept);
		
	}

}
