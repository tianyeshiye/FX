package ty.fx.ohlc.bean;

import ty.fx.bean.TimeDataBaseBean;

public class TimeDataBean extends TimeDataBaseBean {

	// private String timeYMDHM;
	// private String timeH;
	// private float high;
	// private float low;
	// private float open;
	// private float close;
	protected float hlValue;
	protected float ocValue;

	public float getHlValue() {
		return hlValue;
	}

	public void setHlValue(float hlValue) {
		this.hlValue = hlValue;
	}

	public float getOcValue() {
		return ocValue;
	}

	public void setOcValue(float ocValue) {
		this.ocValue = ocValue;
	}

}
