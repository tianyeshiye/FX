package ty.fx.ohlc.bean;

public class ResultBean {

	private String time;
	private float maxValue;
	private float minVale;
	private int count;
	private String persent;

	private float averageV;

	public float getAverageV() {
		return averageV;
	}

	public void setAverageV(float averageV) {
		this.averageV = averageV;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	public float getMinVale() {
		return minVale;
	}

	public void setMinVale(float minVale) {
		this.minVale = minVale;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPersent() {
		return persent;
	}

	public void setPersent(String persent) {
		this.persent = persent;
	}

}
