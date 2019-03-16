package ty.fx.macd.ea;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ty.fx.bean.TradeType;
import ty.fx.macd.bean.MacdDataBean;
import ty.fx.macd.bean.MacdResultBean;

public abstract class MacdParaent {

	public static int DECIMAL_POINT = 0;

	int win = 0;
	int lose = 0;
	float fuKui = 0;
	int timesInterval = 0;

	List<Integer> winTimeIntervalList = new ArrayList<Integer>();
	List<Integer> loseTimeIntervalList = new ArrayList<Integer>();

	List<Float> winPointList = new ArrayList<Float>();
	List<Float> losePointList = new ArrayList<Float>();

	MacdDataBean jinChangBean = null;
	TradeType jinChangType = null; // "买， 卖"

	public void jinChang(MacdDataBean currentBean, TradeType type, List<String> logList) {

		// 进场
		this.jinChangBean = currentBean;
		this.jinChangType = type;

		this.timesInterval = 0;

		logList.add(type.getDesc() + "," + currentBean.toString());
	}

	public void chuChang(MacdDataBean currentBean, TradeType type, List<String> logList,
			List<MacdResultBean> resulList) {

		// 已持有 出场
		float start = jinChangBean.getClose();
		float end = currentBean.getClose();

		BigDecimal jiesuanStart = new BigDecimal(start).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN);
		BigDecimal jiesuanEnd = new BigDecimal(end).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN);

		float jiesuan = 0;

		switch (type) {
		case CHU_Duo:
			// 出 - 买
			jiesuan = jiesuanStart.subtract(jiesuanEnd).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN).floatValue();
			break;
		case CHU_Kong:
			// 出 - 卖
			jiesuan = jiesuanEnd.subtract(jiesuanStart).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN).floatValue();
			break;
		}

		if (jiesuan >= 0) {
			// win
			win++;
			winTimeIntervalList.add(this.timesInterval);
			winPointList.add(jiesuan);

			logList.add(type.getDesc() + "," + currentBean.toString() + ",盈" + ",1" + "," + this.timesInterval);

			MacdResultBean macdResultBean = new MacdResultBean(type.getDesc(), "盈", jiesuan, currentBean);
			resulList.add(macdResultBean);
		} else {
			lose++;
			loseTimeIntervalList.add(this.timesInterval);
			losePointList.add(jiesuan);

			logList.add(type.getDesc() + "," + currentBean.toString() + ",亏" + ",-1" + "," + this.timesInterval);

			MacdResultBean macdResultBean = new MacdResultBean(type.getDesc(), "亏", jiesuan, currentBean);
			resulList.add(macdResultBean);
		}

		BigDecimal fuKuiB = new BigDecimal(fuKui).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN);
		BigDecimal jiesuanB = new BigDecimal(jiesuan).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN);
		fuKui = fuKuiB.add(jiesuanB).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN).floatValue();

		logList.add("总浮亏," + fuKui + ",本次结算," + String.valueOf(jiesuan));

		// clear
		this.jinChangBean = null;
		this.jinChangType = null;
	}
	
	protected void initData() {
		win = 0;
		lose = 0;
		fuKui = 0;
		timesInterval = 0;

		winTimeIntervalList = new ArrayList<Integer>();
		loseTimeIntervalList = new ArrayList<Integer>();

		winPointList = new ArrayList<Float>();
		losePointList = new ArrayList<Float>();

		jinChangBean = null;
		jinChangType = null;
	}

	public boolean isStopLoss() {
		return false;
	}

	public boolean isTargetProfit() {
		return false;
	}
	
	public boolean isBull(MacdDataBean currentBean, MacdDataBean before1Bean, MacdDataBean before2Bean) {

		return true;
	}

	public boolean isBear(MacdDataBean currentBean, MacdDataBean before1Bean, MacdDataBean before2Bean) {

		return true;
	}
	
	public List<String> print(List<MacdDataBean> beanList, List<MacdResultBean> resulList, List<String> logList) {
		int winIntervalAverage = winTimeIntervalList.stream().collect(Collectors.averagingInt(i -> i)).intValue();
		int loseIntervalAverage = loseTimeIntervalList.stream().collect(Collectors.averagingInt(i -> i)).intValue();
		logList.add("winAverageInterval:," + winIntervalAverage);
		logList.add("lostAverageInterval:," + loseIntervalAverage);

		float winAveragePoint = winPointList.stream().collect(Collectors.averagingDouble(i -> i)).floatValue();
		float loseAveragePoint = losePointList.stream().collect(Collectors.averagingDouble(i -> i)).floatValue();
		logList.add("winAveragePoint:," + winAveragePoint);
		logList.add("loseAveragePoint:," + loseAveragePoint);

		logList.add("win:," + win);
		logList.add("lose:," + lose);

		BigDecimal winR = new BigDecimal(win).setScale(0, BigDecimal.ROUND_DOWN);
		BigDecimal totalR = new BigDecimal(win + lose).setScale(0, BigDecimal.ROUND_DOWN);
		float rate = winR.divide(totalR, 2, BigDecimal.ROUND_DOWN).floatValue();

		logList.add("rate:," + rate);
		logList.add("fuKui:," + fuKui);

		System.out.println("winAverageInterval:" + winIntervalAverage);
		System.out.println("lostAverageInterval:" + loseIntervalAverage);

		System.out.println("winAveragePoint:" + winAveragePoint);
		System.out.println("loseAveragePoint:" + loseAveragePoint);

		System.out.println("win:" + win);
		System.out.println("lose:" + lose);

		System.out.println("rate:" + rate);
		System.out.println("fuKui:" + fuKui);

		return logList;
	}
	
	public List<String> ea(List<MacdDataBean> beanList, List<MacdResultBean> resulList) {

		initData();

		List<String> logList = new ArrayList<String>();

		for (int i = 0; i < beanList.size(); i++) {

			this.timesInterval++;

			MacdDataBean before1Bean;
			MacdDataBean before2Bean;

			if (i == 0) {
				before1Bean = Macds.getNullBean();
				before2Bean = Macds.getNullBean();
			} else if (i == 1) {

				before1Bean = beanList.get(0);
				before2Bean = Macds.getNullBean();
			} else {
				before1Bean = beanList.get(i - 1);
				before2Bean = beanList.get(i - 2);
			}

			MacdDataBean currentBean = beanList.get(i);

			// 由 负 -> 正
			if (isBull(currentBean, before1Bean, before2Bean)) {

				if (jinChangBean != null) {
					// 已持有
					// 出场 - 买
					chuChang(currentBean, TradeType.CHU_Duo, logList, resulList);
				}
				// 进场 - 买
				jinChang(currentBean, TradeType.JIN_Duo, logList);
			}
			// 由 正 -> 负
			if (isBear(currentBean, before1Bean, before2Bean)) {

				if (jinChangBean != null) {
					// 已持有
					// 出场 - 卖
					chuChang(currentBean, TradeType.CHU_Kong, logList, resulList);
				}
				// 进场 - 卖
				jinChang(currentBean, TradeType.JIN_Kong, logList);
			}
			
			// 止损
			if (isStopLoss()) {
				
			}
			// 止盈
if (isTargetProfit()) {
				
			}
		}

		print(beanList, resulList, logList);

		return logList;
	}

}
