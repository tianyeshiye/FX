package ty.fx.macd.bean;

import ty.fx.bean.TimeDataBaseBean;

public class MacdDataBean extends TimeDataBaseBean {

	// private String timeYMDHM;
	// private String timeH;
	// private float high;
	// private float low;
	// private float open;
	// private float close;

	protected float slowEMA;
	protected float fastEMA;

	protected float dif;
	protected float dea;

	public String toString() {
		return "timeYMDHM:" + timeYMDHM + ", " + 
				"timeH:" + timeH + ", " + 
				"open:" + open + ", " +
				"high:" + high + ", " + 
				"low:" + low + ", " + 
				"close:" + close;
	}

	public float getDif() {
		return dif;
	}

	public void setDif(float dif) {
		this.dif = dif;
	}

	public float getDea() {
		return dea;
	}

	public void setDea(float dea) {
		this.dea = dea;
	}

	public float getFastEMA() {
		return fastEMA;
	}

	public void setFastEMA(float fastEMA) {
		this.fastEMA = fastEMA;
	}

	public float getSlowEMA() {
		return slowEMA;
	}

	public void setSlowEMA(float slowEMA) {
		this.slowEMA = slowEMA;
	}

}
