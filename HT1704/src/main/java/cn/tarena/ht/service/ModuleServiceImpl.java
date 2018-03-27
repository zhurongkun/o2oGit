package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.ModuleMapper;
import cn.tarena.ht.pojo.Module;
@Service
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	private ModuleMapper moduleMapper;
	
	
	@Override
	public List<Module> findAll() {
		
		return moduleMapper.findAll();
	}


	@Override
	public void updateState(int state, String[] moduleIds) {
		
		moduleMapper.updateState(state,moduleIds);
	}


	@Override
	public void deleteModules(String[] moduleIds) {
		
		moduleMapper.deleteModules(moduleIds);
	}


	@Override
	public List<Module> findParentList() {
		
		return moduleMapper.findParentList();
	}


	@Override
	public void saveModule(Module module) {
		
		//准备数据
		module.setModuleId(UUID.randomUUID().toString());
		module.setCreateTime(new Date());
		
		moduleMapper.saveModule(module);
	}


	@Override
	public Module findOne(String moduleId) {
		
		return moduleMapper.findOne(moduleId);
	}

}
