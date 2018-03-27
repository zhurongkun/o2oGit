package cn.tarena.ht.pojo;
//部门信息
public class Dept extends BaseEntity{
	
	private Dept parentDept;  //一对一 自关联
	private String deptId;
	private String deptName;
	private Integer state;  //0表示停用   1.表示启用
	
	public Dept getParentDept() {
		return parentDept;
	}
	public void setParentDept(Dept parentDept) {
		this.parentDept = parentDept;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	

}
