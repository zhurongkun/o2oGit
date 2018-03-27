package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Module;

public interface ModuleService {
	public List<Module> findAll();

	public void updateState(int state, String[] moduleIds);

	public void deleteModules(String[] moduleIds);

	public List<Module> findParentList();

	public void saveModule(Module module);

	public Module findOne(String moduleId);
}
