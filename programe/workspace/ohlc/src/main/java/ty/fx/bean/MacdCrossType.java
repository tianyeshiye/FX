package ty.fx.bean;

public enum MacdCrossType {

	GOLD("1", "金叉"), 
	DEAD("2", "死叉"),
	NULL("2", "null");

	private String value;
	private String desc;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private MacdCrossType(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}
}
