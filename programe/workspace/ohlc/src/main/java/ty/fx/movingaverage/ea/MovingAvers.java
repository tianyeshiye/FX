package ty.fx.movingaverage.ea;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ty.fx.bean.TimeDataBaseBean;
import ty.fx.movingaverage.bean.MovingAverBean;
import ty.fx.movingaverage.bean.MovingResultBean;

public class MovingAvers {

	public static List<MovingAverBean> calculate(List<TimeDataBaseBean> baseDataList, final int fast, final int slow,
			int decimalPointPara) {

		List<MovingAverBean> movingAverBeanList = new ArrayList<MovingAverBean>();

		List<TimeDataBaseBean> fastList = new ArrayList<TimeDataBaseBean>();
		List<TimeDataBaseBean> slowList = new ArrayList<TimeDataBaseBean>();

		for (int i = 0; i < baseDataList.size(); i++) {

			TimeDataBaseBean currentTimeDataBaseBean = baseDataList.get(i);

			if (fastList.size() == fast) {
				fastList.remove(0);
				fastList.add(currentTimeDataBaseBean);
			} else {
				fastList.add(currentTimeDataBaseBean);
			}

			if (slowList.size() == slow) {
				slowList.remove(0);
				slowList.add(currentTimeDataBaseBean);
			} else {
				slowList.add(currentTimeDataBaseBean);
			}

			MovingAverBean movingAverBean = new MovingAverBean(currentTimeDataBaseBean);

			calculateMovingAver(movingAverBean, fastList, slowList, decimalPointPara, fast, slow);

			movingAverBeanList.add(movingAverBean);
		}

		return movingAverBeanList;
	}

	private static void calculateMovingAver(MovingAverBean movingAverBean, List<TimeDataBaseBean> fastList,
			List<TimeDataBaseBean> slowList, int decimalPointPara, final int fast, final int slow) {

		if (slowList.size() < slow) {
			movingAverBean.setFastMovingAver(0);
			movingAverBean.setSlowMovingAver(0);

			return;
		}

		double fastSum = fastList.stream().mapToDouble(TimeDataBaseBean::getClose).sum();
		double slowSum = slowList.stream().mapToDouble(TimeDataBaseBean::getClose).sum();

		BigDecimal fastSumBigDecimal = new BigDecimal(fastSum).setScale(decimalPointPara, BigDecimal.ROUND_DOWN);
		BigDecimal fastSize = new BigDecimal(fastList.size()).setScale(decimalPointPara, BigDecimal.ROUND_DOWN);

		BigDecimal slowSumBigDecimal = new BigDecimal(slowSum).setScale(decimalPointPara, BigDecimal.ROUND_DOWN);
		BigDecimal slowSize = new BigDecimal(slowList.size()).setScale(decimalPointPara, BigDecimal.ROUND_DOWN);

		float faseMoving = fastSumBigDecimal.divide(fastSize, RoundingMode.DOWN).floatValue();
		float slowMoving = slowSumBigDecimal.divide(slowSize, RoundingMode.DOWN).floatValue();

		movingAverBean.setFastMovingAver(faseMoving);
		movingAverBean.setSlowMovingAver(slowMoving);
	}

	public static MovingAverBean getNullBean() {

		MovingAverBean bean = new MovingAverBean();
		bean.setClose(0);
		bean.setOpen(0);
		bean.setHigh(0);
		bean.setLow(0);
		bean.setFastMovingAver(0);
		bean.setSlowMovingAver(0);

		return bean;
	}

	public static List<String> printResult(List<MovingResultBean> resulList) {

		List<String> list = new ArrayList<String>();

		resulList.forEach(movingResultBean -> {

			list.add(movingResultBean.toString());
		});

		return list;
	}

	public static List<String> printSortResult(List<MovingResultBean> resulList) {

		PointComparator compareAverage = new PointComparator();
		Collections.sort(resulList, compareAverage);

		List<String> list = new ArrayList<String>();

		resulList.forEach(movingResultBean -> {

			list.add(movingResultBean.toString());
		});

		return list;
	}

	public static class PointComparator implements Comparator<MovingResultBean> {

		@Override
		public int compare(MovingResultBean bean1, MovingResultBean bean2) {
			double diff = bean1.getPoint() - bean2.getPoint();
			if (diff > 0) {
				return -1;
			} else if (diff < 0) {
				return 1;
			}
			return 0;
		}
	}
}
