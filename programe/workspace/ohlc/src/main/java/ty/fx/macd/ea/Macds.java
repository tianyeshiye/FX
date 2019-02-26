package ty.fx.macd.ea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ty.fx.macd.bean.MacdDataBean;
import ty.fx.macd.bean.MacdResultBean;

public class Macds {

	public static void calculateMacd(MacdDataBean beforeBean, MacdDataBean currentBean, final int fast, final int slow,
			final int bar) {

		MathContext mc = new MathContext(5, RoundingMode.HALF_DOWN);

		// EMA（12）=55.01+(53.7-55.01)×2/13=54.8085
		// EMA（26）=55.01+(53.7-55.01)×2/27=54.913
		// EMA（12）= 前一日EMA（12）×11/13＋今日收盘价×2/13
		// EMA（26）= 前一日EMA（26）×25/27＋今日收盘价×2/27

		BigDecimal beforeFastEma = new BigDecimal(beforeBean.getFastEMA()).setScale(5, BigDecimal.ROUND_DOWN);
		BigDecimal beforeSlowEma = new BigDecimal(beforeBean.getSlowEMA()).setScale(5, BigDecimal.ROUND_DOWN);

		// close
		BigDecimal close = new BigDecimal(currentBean.getClose()).setScale(5, BigDecimal.ROUND_DOWN);

		BigDecimal int2 = new BigDecimal(2).setScale(0, BigDecimal.ROUND_DOWN);
		BigDecimal int13 = new BigDecimal(fast + 1).setScale(0, BigDecimal.ROUND_DOWN);
		BigDecimal int27 = new BigDecimal(slow + 1).setScale(0, BigDecimal.ROUND_DOWN);

		float fastEma = beforeFastEma.add(close.subtract(beforeFastEma).multiply(int2).divide(int13, mc)).floatValue();

		float slowEma = beforeSlowEma.add(close.subtract(beforeSlowEma).multiply(int2).divide(int27, mc)).floatValue();

		currentBean.setFastEMA(fastEma);
		currentBean.setSlowEMA(slowEma);

		// DIFF=今日EMA（12）- 今日EMA（26）
		currentBean.setDif(fastEma - slowEma);

		BigDecimal int10 = new BigDecimal(bar + 1).setScale(0, BigDecimal.ROUND_DOWN);
		BigDecimal beforeDEA = new BigDecimal(beforeBean.getDea()).setScale(5, BigDecimal.ROUND_DOWN);
		BigDecimal currentDif = new BigDecimal(currentBean.getDif()).setScale(5, BigDecimal.ROUND_DOWN);
		// DEA（MACD）= 前一日DEA×8/10＋今日DIF×2/10
		float dea = beforeDEA.add(currentDif.subtract(beforeDEA).multiply(int2).divide(int10, mc)).floatValue();

		currentBean.setDea(dea);
	}

	public static void calculateMacd(List<MacdDataBean> beanList, int fast, int slow, int signal) {

		for (int i = 0; i < beanList.size(); i++) {

			MacdDataBean beforeBean = null;
			if (i == 0) {
				beforeBean = Macds.getNullBean();
			} else {
				beforeBean = beanList.get(i - 1);
			}

			MacdDataBean currentBean = beanList.get(i);

			Macds.calculateMacd(beforeBean, currentBean, fast, slow, signal);
		}

	}

	

	public static MacdDataBean getNullBean() {

		MacdDataBean bean = new MacdDataBean();
		bean.setDif(0);
		bean.setDea(0);
		bean.setClose(0);
		bean.setOpen(0);
		bean.setHigh(0);
		bean.setLow(0);
		bean.setFastEMA(0);
		bean.setSlowEMA(0);
		;

		return bean;
	}

	public static List<String> printResult(List<MacdResultBean> resulList) {

		List<String> list = new ArrayList<String>();

		resulList.forEach(macdResultBean -> {

			list.add(macdResultBean.toString());
		});

		return list;
	}

	public static List<String> printSortResult(List<MacdResultBean> resulList) {

		PointComparator compareAverage = new PointComparator();
		Collections.sort(resulList, compareAverage);

		List<String> list = new ArrayList<String>();

		resulList.forEach(macdResultBean -> {

			list.add(macdResultBean.toString());
		});

		return list;
	}

	public static class PointComparator implements Comparator<MacdResultBean> {

		@Override
		public int compare(MacdResultBean bean1, MacdResultBean bean2) {
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
