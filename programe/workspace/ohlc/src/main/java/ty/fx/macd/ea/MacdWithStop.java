package ty.fx.macd.ea;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ty.fx.bean.TradeType;
import ty.fx.macd.bean.MacdDataBean;
import ty.fx.macd.bean.MacdResultBean;

public class MacdWithStop {

	public MacdWithStop(int decimalPointPara, float stopPoint) {
		DECIMAL_POINT = decimalPointPara;
		STOP_POINT = stopPoint;
	}

	public static int DECIMAL_POINT = 0;
	public static float STOP_POINT = 0;

	int win = 0;
	int lose = 0;
	float fuKui = 0;

	float stop = 0;

	private MacdDataBean jinChangBean = null;
	private TradeType jinChangType = null; // "买， 卖"

	private void initData() {
		win = 0;
		lose = 0;
		fuKui = 0;

		stop = 0;

		jinChangBean = null;
		jinChangType = null;
	}

	public List<String> ea(List<MacdDataBean> beanList, List<MacdResultBean> resulList) {

		initData();

		List<String> logList = new ArrayList<String>();

		for (int i = 0; i < beanList.size(); i++) {

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

			// 判断是否止损 stop
			if (jinChangBean != null) {

				BigDecimal stopBigDecimal = new BigDecimal(STOP_POINT).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN);
				BigDecimal startBigDecimal = new BigDecimal(jinChangBean.getClose()).setScale(DECIMAL_POINT,
						BigDecimal.ROUND_DOWN);
				BigDecimal currentHighBigDecimal = new BigDecimal(currentBean.getHigh()).setScale(DECIMAL_POINT,
						BigDecimal.ROUND_DOWN);
				BigDecimal currentLowBigDecimal = new BigDecimal(currentBean.getLow()).setScale(DECIMAL_POINT,
						BigDecimal.ROUND_DOWN);

				switch (jinChangType) {
				case JIN_Duo:
					float point1 = startBigDecimal.subtract(stopBigDecimal).subtract(currentLowBigDecimal)
							.setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN).floatValue();

					if (point1 > 0) {

						stop = startBigDecimal.subtract(stopBigDecimal).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN)
								.floatValue();

						if (stop > currentBean.getHigh()) {
							stop = currentBean.getHigh();
						}

						// 出场 - 卖
						chuChangStop(currentBean, TradeType.CHU_Kong, logList, resulList, stop);
					}
					break;
				case JIN_Kong:

					float point2 = startBigDecimal.add(stopBigDecimal).subtract(currentHighBigDecimal)
							.setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN).floatValue();

					if (point2 < 0) {

						stop = startBigDecimal.add(stopBigDecimal).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN)
								.floatValue();

						if (stop < currentBean.getLow()) {
							stop = currentBean.getLow();
						}

						// 出场 - 买
						chuChangStop(currentBean, TradeType.CHU_Duo, logList, resulList, stop);
					}

					break;
				default:
					break;
				}
			}

			// 由 负 -> 正
			if ((currentBean.getDif() > 0 && before1Bean.getDif() == 0 && before2Bean.getDif() < 0)
					|| (currentBean.getDif() > 0 && before1Bean.getDif() < 0)) {

				if (jinChangBean != null) {
					// 已持有
					// 出场 - 买
					chuChang(currentBean, TradeType.CHU_Duo, logList, resulList);
				}
				// 进场 - 买
				jinChang(currentBean, TradeType.JIN_Duo, logList);
			}
			// 由 正 -> 负
			if ((currentBean.getDif() < 0 && before1Bean.getDif() == 0 && before2Bean.getDif() > 0)
					|| (currentBean.getDif() < 0 && before1Bean.getDif() > 0)) {

				if (jinChangBean != null) {
					// 已持有
					// 出场 - 卖
					chuChang(currentBean, TradeType.CHU_Kong, logList, resulList);
				}
				// 进场 - 卖
				jinChang(currentBean, TradeType.JIN_Kong, logList);
			}
		}

		logList.add("win," + win);
		logList.add("lose," + lose);

		BigDecimal winR = new BigDecimal(win).setScale(0, BigDecimal.ROUND_DOWN);
		BigDecimal totalR = new BigDecimal(win + lose).setScale(0, BigDecimal.ROUND_DOWN);
		float rate = winR.divide(totalR, 2, BigDecimal.ROUND_DOWN).floatValue();

		logList.add("rate," + rate);
		logList.add("fuKui," + fuKui);

		System.out.println("win:" + win);
		System.out.println("lose:" + lose);
		System.out.println("rate:" + rate);
		System.out.println("fuKui:" + fuKui);

		return logList;
	}

	private void jinChang(MacdDataBean currentBean, TradeType type, List<String> logList) {

		// 进场
		this.jinChangBean = currentBean;
		this.jinChangType = type;

		logList.add(type.getDesc() + "," + currentBean.toString());
	}

	private void chuChangStop(MacdDataBean currentBean, TradeType type, List<String> logList,
			List<MacdResultBean> resulList, float stop) {

		// 已持有 出场
		float start = jinChangBean.getClose();
		float end = stop;

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
			logList.add(type.getDesc() + "," + currentBean.toString() + ",盈" + ",1");

			MacdResultBean macdResultBean = new MacdResultBean(type.getDesc(), "盈", jiesuan, currentBean);
			resulList.add(macdResultBean);
		} else {
			lose++;
			logList.add(type.getDesc() + "," + currentBean.toString() + ",亏" + ",-1");

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
		this.stop = 0;
	}

	private void chuChang(MacdDataBean currentBean, TradeType type, List<String> logList,
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
			logList.add(type.getDesc() + "," + currentBean.toString() + ",盈" + ",1");

			MacdResultBean macdResultBean = new MacdResultBean(type.getDesc(), "盈", jiesuan, currentBean);
			resulList.add(macdResultBean);
		} else {
			lose++;
			logList.add(type.getDesc() + "," + currentBean.toString() + ",亏" + ",-1");

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

}
