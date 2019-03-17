package ty.fx.bean;

public enum TradeType {

	JIN_Duo("1", "进 - 买"), 
	JIN_Kong("2", "进 - 卖"), 
	CHU_Duo("3", "出 - 买"), 
	CHU_Kong("4", "出 - 卖"),
	HOLD_NULL("9", "空仓");

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

	private TradeType(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}
}
