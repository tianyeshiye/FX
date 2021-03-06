package ty.fx.macd.bean;

public class MacdResultBean extends MacdDataBean {

	private String inOutType;
	
	private String yK;
	
	private float point;
	
	private float maxPoint;
	
	public MacdResultBean(MacdDataBean bean) {
		this.timeYMDHM = bean.getTimeYMDHM();
		this.timeH= bean.getTimeH();
		this.high= bean.getHigh();
		this.low= bean.getLow();
		this.open= bean.getOpen();
		this.close= bean.getClose();

		this.slowEMA= bean.getSlowEMA();
		this.fastEMA= bean.getFastEMA();

		this.dif= bean.getDif();
		this.dea= bean.getDea();
	}

	public MacdResultBean(String type, String yk, float point, MacdDataBean bean) {
		this.inOutType = type;
		this.yK = yk;
		this.point = point;
		
		this.timeYMDHM = bean.getTimeYMDHM();
		this.timeH= bean.getTimeH();
		this.high= bean.getHigh();
		this.low= bean.getLow();
		this.open= bean.getOpen();
		this.close= bean.getClose();

		this.slowEMA= bean.getSlowEMA();
		this.fastEMA= bean.getFastEMA();

		this.dif= bean.getDif();
		this.dea= bean.getDea();
	}
	
	public String toString() {
		
		int ykInt;
		if (yK.equals("盈")) {
			ykInt = 1;
		} else {
			ykInt = -1;
		}
		
		return point + ", " + 
				inOutType + ", " + 
				yK + ", " + 
				ykInt + ", " + 
				"timeYMDHM:" + timeYMDHM + ", " + 
				"timeH:" + timeH + ", " + 
				"open:" + open + ", " +
				"high:" + high + ", " + 
				"low:" + low + ", " + 
				"close:" + close + ", " + 
				"dif:" + dif + ", " + 
				"dea:" + dea;
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
