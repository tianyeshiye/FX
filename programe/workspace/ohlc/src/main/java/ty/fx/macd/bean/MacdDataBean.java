package ty.fx.macd.bean;

import ty.fx.bean.TimeDataBaseBean;

public class MacdDataBean extends TimeDataBaseBean {

	// private String timeYMDHM;
	// private String timeH;
	// private float high;
	// private float low;
	// private float open;
	// private float close;

	public MacdDataBean() {

	}
	


	public MacdDataBean(TimeDataBaseBean currentTimeDataBaseBean) {

		this.timeYMDHM = currentTimeDataBaseBean.getTimeYMDHM();
		this.timeH = currentTimeDataBaseBean.getTimeH();
		this.high = currentTimeDataBaseBean.getHigh();
		this.low = currentTimeDataBaseBean.getLow();
		this.open = currentTimeDataBaseBean.getOpen();
		this.close = currentTimeDataBaseBean.getClose();
	}

	public float slowEMA;
	public float fastEMA;

	public float dif;
	public float dea;
	
	
	public String toString() {
		return "dif:" + dif + ", " + 
				"dea:" + dea + ", " + 
				"timeYMDHM:" + timeYMDHM + ", " + 
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
