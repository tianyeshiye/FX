package ty.fx.bean;

public enum YingKuiType {

	WIN("1", "盈"), LOSE("2", "亏");

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

	private YingKuiType(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}
}
