package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.tarena.ht.pojo.Module;

public interface ModuleMapper {
	public List<Module> findAll();

	public void updateState(@Param("state")int state,@Param("moduleIds")String[] moduleIds);

	public void deleteModules(String[] moduleIds);
	
	@Select("select * from module_p")
	public List<Module> findParentList();

	public void saveModule(Module module);

	public Module findOne(String moduleId);
}
