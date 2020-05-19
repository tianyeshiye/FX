package ty.fx.movingaverage.ea;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ty.fx.bean.TradeType;
import ty.fx.movingaverage.bean.MovingAverBean;
import ty.fx.movingaverage.bean.MovingResultBean;

public abstract class MovingParentEa {

	public void initConstructor(int decimalPointPara, int zhiyingPoint) {

		DECIMAL_POINT = decimalPointPara;
	}

	public static int DECIMAL_POINT = 0;

	int win = 0;
	int lose = 0;
	float fuKui = 0;
	int timesInterval = 0;

	List<Integer> winTimeIntervalList = new ArrayList<Integer>();
	List<Integer> loseTimeIntervalList = new ArrayList<Integer>();

	List<Float> winPointList = new ArrayList<Float>();
	List<Float> losePointList = new ArrayList<Float>();

	MovingAverBean jinChangBean = null;
	TradeType jinChangType = TradeType.HOLD_NULL; // "买， 卖"

	public void jinChang(MovingAverBean currentBean, TradeType type, List<String> logList) {

		// 进场
		this.jinChangBean = currentBean;
		this.jinChangType = type;

		this.timesInterval = 0;

		logList.add(type.getDesc() + "," + currentBean.toString());
	}

	public void chuChang(MovingAverBean currentBean, TradeType type, List<String> logList,
			List<MovingResultBean> resulList) {

		// 已持有 出场
		float start = jinChangBean.getClose();
		float end = currentBean.getClose();

		BigDecimal jiesuanStart = new BigDecimal(start).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN);
		BigDecimal jiesuanEnd = new BigDecimal(end).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN);

		float jiesuan = 0;

		switch (this.jinChangType) {

		case JIN_Duo:
			// 进 - 买
			// 出 - 卖
			jiesuan = jiesuanEnd.subtract(jiesuanStart).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN).floatValue();
			break;
		case JIN_Kong:
			// 进 - 卖
			// 出 - 买
			jiesuan = jiesuanStart.subtract(jiesuanEnd).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN).floatValue();
			break;
		}

		MovingResultBean movingResultBean = new MovingResultBean(currentBean);

		// 出场类型
		movingResultBean.setInOutType(type.getDesc());
		// 盈亏点数
		movingResultBean.setPoint(jiesuan);

		if (jiesuan >= 0) {
			movingResultBean.setYK("盈");

			// win
			win++;
			winTimeIntervalList.add(this.timesInterval);
			winPointList.add(jiesuan);


				logList.add(type.getDesc() + "," + currentBean.toString() + ",盈" + ",1" + "," + this.timesInterval);
			

		} else {
			movingResultBean.setYK("亏");

			lose++;
			loseTimeIntervalList.add(this.timesInterval);
			losePointList.add(jiesuan);


				logList.add(type.getDesc() + "," + currentBean.toString() + ",亏" + ",-1" + "," + this.timesInterval);

		}

		// 结果list 增加
		resulList.add(movingResultBean);

		BigDecimal fuKuiB = new BigDecimal(fuKui).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN);
		BigDecimal jiesuanB = new BigDecimal(jiesuan).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN);
		fuKui = fuKuiB.add(jiesuanB).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN).floatValue();

		logList.add("总浮亏," + fuKui + ",本次结算," + String.valueOf(jiesuan));

		// clear current status
		clearCurrentStatus();
	}

	public List<String> ea(List<MovingAverBean> beanList, List<MovingResultBean> resulList) {

		initData();

		List<String> logList = new ArrayList<String>();

		for (int i = 0; i < beanList.size(); i++) {

			MovingAverBean before1Bean;
			MovingAverBean before2Bean;

			if (i == 0) {
				before1Bean = MovingAvers.getNullBean();
				before2Bean = MovingAvers.getNullBean();
			} else if (i == 1) {

				before1Bean = beanList.get(0);
				before2Bean = MovingAvers.getNullBean();
			} else {
				before1Bean = beanList.get(i - 1);
				before2Bean = beanList.get(i - 2);
			}

			MovingAverBean currentBean = beanList.get(i);

			this.timesInterval++;

			boolean gangJinChang = false;
			
			// 由 负 -> 正
			if (isBull(currentBean, before1Bean, before2Bean)) {

				if (jinChangBean != null) {
					// 已持有
					// 出场 - 买
					chuChang(currentBean, TradeType.CHU_Duo, logList, resulList);
				}
				// 进场 - 买
				jinChang(currentBean, TradeType.JIN_Duo, logList);
				
				gangJinChang = true;
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
				
				gangJinChang = true;
			}

			// 已持有 判断止盈止损
			if (!gangJinChang && jinChangBean != null) {

				// 止损
				if (isStopLoss(currentBean, before1Bean, before2Bean)) {

				}

				// 止盈
				if (isTargetProfit(currentBean, before1Bean, before2Bean)) {

					// 出场 - 止盈
					chuChang(currentBean, TradeType.CHU_ZhiYing, logList, resulList);
				}
			}
		}

		print(beanList, resulList, logList);

		return logList;
	}

	private List<String> print(List<MovingAverBean> beanList, List<MovingResultBean> resulList, List<String> logList) {
		int winIntervalAverage = winTimeIntervalList.stream().collect(Collectors.averagingInt(i -> i)).intValue();
		int loseIntervalAverage = loseTimeIntervalList.stream().collect(Collectors.averagingInt(i -> i)).intValue();

		float winAveragePoint = winPointList.stream().collect(Collectors.averagingDouble(i -> i)).floatValue();
		float loseAveragePoint = losePointList.stream().collect(Collectors.averagingDouble(i -> i)).floatValue();

		float winLoseRate = getRate(win, lose);

		logList.add("winAverageInterval:," + winIntervalAverage);
		logList.add("lostAverageInterval:," + loseIntervalAverage);

		logList.add("winAveragePoint:," + winAveragePoint);
		logList.add("loseAveragePoint:," + loseAveragePoint);

		logList.add("win:," + win);
		logList.add("lose:," + lose);
		logList.add("真实盈亏比:," + winLoseRate);

		System.out.println("winAverageInterval:" + winIntervalAverage);
		System.out.println("lostAverageInterval:" + loseIntervalAverage);

		System.out.println("winAveragePoint:" + winAveragePoint);
		System.out.println("loseAveragePoint:" + loseAveragePoint);

		System.out.println("win:" + win);
		System.out.println("lose:" + lose);
		System.out.println("真实盈亏比:" + winLoseRate);

		// 最大间隔 判断进场后是否能盈利的概率
		long winChangesSum = resulList.stream().filter(movingResultBean -> {
			if (movingResultBean.getYK().equals("盈")) {
				return true;
			} else {
				return false;
			}
		}).count();
		long loseChangesSum = resulList.size() - winChangesSum;

		logList.add("盈的次数:," + winChangesSum);
		logList.add("亏的次数:," + loseChangesSum);
		logList.add("盈亏的概率比:," + getRate(winChangesSum, loseChangesSum));
		logList.add("总浮亏:," + fuKui);

		System.out.println("盈的次数:" + winChangesSum);
		System.out.println("亏的次数:" + loseChangesSum);
		System.out.println("盈亏的概率比:" + getRate(winChangesSum, loseChangesSum));
		System.out.println("总浮亏:" + fuKui);

		return logList;
	}

	private float getRate(long win, long lose) {

		BigDecimal winR = new BigDecimal(win).setScale(0, BigDecimal.ROUND_DOWN);
		BigDecimal totalR = new BigDecimal(win + lose).setScale(0, BigDecimal.ROUND_DOWN);
		float rate = winR.divide(totalR, 2, BigDecimal.ROUND_DOWN).floatValue();

		return rate;
	}

	private void clearCurrentStatus() {
		this.jinChangBean = null;
		this.jinChangType = TradeType.HOLD_NULL;
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

		clearCurrentStatus();
	}

	protected abstract boolean isStopLoss(MovingAverBean currentBean, MovingAverBean before1Bean, MovingAverBean before2Bean);

	protected abstract boolean isTargetProfit(MovingAverBean currentBean, MovingAverBean before1Bean,
			MovingAverBean before2Bean);

	protected abstract boolean isBull(MovingAverBean currentBean, MovingAverBean before1Bean, MovingAverBean before2Bean);

	protected abstract boolean isBear(MovingAverBean currentBean, MovingAverBean before1Bean, MovingAverBean before2Bean);
}
