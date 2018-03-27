package cn.tarena.ht.pojo;

public class Role extends BaseEntity{
	private String roleId;
	private String name; //角色名称
	private String remarks; //备注信息
	private Integer orderNo; //排序号
	private Boolean checked; //为了数据回显需要
	
	/**
	 * 根据zTree树的需要 id ,name属性 ,checked,需要手写getId()
	 * @return
	 */
	
	
	
	public String getId(){
		return roleId;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
