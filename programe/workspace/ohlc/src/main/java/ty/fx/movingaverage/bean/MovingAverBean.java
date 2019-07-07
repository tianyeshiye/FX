package ty.fx.movingaverage.bean;

import ty.fx.bean.TimeDataBaseBean;

public class MovingAverBean extends TimeDataBaseBean {

	protected float slowMovingAver;
	protected float fastMovingAver;

	public MovingAverBean() {

	}

	public MovingAverBean(TimeDataBaseBean currentTimeDataBaseBean) {

		this.timeYMDHM = currentTimeDataBaseBean.getTimeYMDHM();
		this.timeH = currentTimeDataBaseBean.getTimeH();
		this.high = currentTimeDataBaseBean.getHigh();
		this.low = currentTimeDataBaseBean.getLow();
		this.open = currentTimeDataBaseBean.getOpen();
		this.close = currentTimeDataBaseBean.getClose();
	}

	public float getSlowMovingAver() {
		return slowMovingAver;
	}

	public void setSlowMovingAver(float slowMovingAver) {
		this.slowMovingAver = slowMovingAver;
	}

	public float getFastMovingAver() {
		return fastMovingAver;
	}

	public void setFastMovingAver(float fastMovingAver) {
		this.fastMovingAver = fastMovingAver;
	}
}
