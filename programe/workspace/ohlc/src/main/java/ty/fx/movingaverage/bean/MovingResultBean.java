package ty.fx.movingaverage.bean;

public class MovingResultBean extends MovingAverBean {

	private String inOutType;

	private String yK;

	private float point;

	private float maxPoint;

	public MovingResultBean(MovingAverBean bean) {
		this.timeYMDHM = bean.getTimeYMDHM();
		this.timeH = bean.getTimeH();
		this.high = bean.getHigh();
		this.low = bean.getLow();
		this.open = bean.getOpen();
		this.close = bean.getClose();

		this.slowMovingAver = bean.getSlowMovingAver();
		this.fastMovingAver = bean.getFastMovingAver();
	}

	public MovingResultBean(String type, String yk, float point, MovingAverBean bean) {
		this.inOutType = type;
		this.yK = yk;
		this.point = point;

		this.timeYMDHM = bean.getTimeYMDHM();
		this.timeH = bean.getTimeH();
		this.high = bean.getHigh();
		this.low = bean.getLow();
		this.open = bean.getOpen();
		this.close = bean.getClose();

		this.slowMovingAver = bean.getSlowMovingAver();
		this.fastMovingAver = bean.getFastMovingAver();
	}

	public String toString() {

		int ykInt;
		if (yK.equals("盈")) {
			ykInt = 1;
		} else {
			ykInt = -1;
		}

		return point + ", " + inOutType + ", " + yK + ", " + ykInt + ", " + "timeYMDHM:" + timeYMDHM + ", " + "timeH:"
				+ timeH + ", " + "open:" + open + ", " + "high:" + high + ", " + "low:" + low + ", " + "close:" + close
				+ ", " + "dif:" + fastMovingAver + ", " + "dea:" + slowMovingAver;
	}

	public float getPoint() {
		return point;
	}

	public void setPoint(float point) {
		this.point = point;
	}

	public String getInOutType() {
		return inOutType;
	}

	public void setInOutType(String inOutType) {
		this.inOutType = inOutType;
	}

	public String getYK() {
		return yK;
	}

	public void setYK(String yK) {
		this.yK = yK;
	}

	public float getMaxPoint() {
		return maxPoint;
	}

	public void setMaxPoint(float maxPoint) {
		this.maxPoint = maxPoint;
	}

}
