package cn.tarena.ht.pojo;
//模块信息
public class Module extends BaseEntity{
	private String moduleId;
	private Module parentModule;  //一对一关联
	private String name;    //模块名称
	private Integer ctype;  //模块类型
	private Integer state;  //状态
	private Integer orderNo; //排序号
	private String remark;   //备注信息
	private Boolean checked; //zTree的回显操作
	
	
	
	//为了ZTree树展现数据 添加getId()
	
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getId(){
		return moduleId;
	}
	
	//为了展现上下级关系,编辑pId
	public String getpId(){
		//判断是否有上级
		if(parentModule !=null){
			return parentModule.getModuleId();
		}
		return null;	
	}
	
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public Module getParentModule() {
		return parentModule;
	}
	public void setParentModule(Module parentModule) {
		this.parentModule = parentModule;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCtype() {
		return ctype;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
