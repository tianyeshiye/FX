package ty.fx.macd.ea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import ty.fx.macd.bean.MacdDataBean;
import ty.fx.macd.bean.MacdResultBean;
import ty.fx.utils.ExcelFileUtils;

public class MacdBackup {

	private static final SimpleDateFormat dataFormat_yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

	public class PointComparator implements Comparator<MacdResultBean> {

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

	public List<String> printResult(List<MacdResultBean> resulList) {

		List<String> list = new ArrayList<String>();

		resulList.forEach(macdResultBean -> {

			list.add(macdResultBean.toString());
		});

		return list;
	}

	public List<String> printSortResult(List<MacdResultBean> resulList) {

		PointComparator compareAverage = new PointComparator();
		Collections.sort(resulList, compareAverage);

		List<String> list = new ArrayList<String>();

		resulList.forEach(macdResultBean -> {

			list.add(macdResultBean.toString());
		});

		return list;
	}

	public List<String> ea(List<MacdDataBean> beanList, List<MacdResultBean> resulList) {

		List<String> outputList = new ArrayList<String>();

		int win = 0;
		int lose = 0;
		float fuKui = 0;

		float start = 0;
		float end = 0;

		for (int i = 0; i < beanList.size(); i++) {

			MacdDataBean before1Bean;
			MacdDataBean before2Bean;

			if (i == 0) {
				before1Bean = getNullBean();
				before2Bean = getNullBean();
			} else if (i == 1) {

				before1Bean = beanList.get(0);
				before2Bean = getNullBean();
			} else {
				before1Bean = beanList.get(i - 1);
				before2Bean = beanList.get(i - 2);
			}

			MacdDataBean currentBean = beanList.get(i);

			// fase 由 负 -> 正
			if ((currentBean.getDif() > 0 && before1Bean.getDif() == 0 && before2Bean.getDif() < 0)
					|| (currentBean.getDif() > 0 && before1Bean.getDif() < 0)) {

				if (start != 0) {
					// 已持有
					// 出场
					end = currentBean.getClose();

					BigDecimal jiesuanEnd = new BigDecimal(end).setScale(5, BigDecimal.ROUND_DOWN);
					BigDecimal jiesuanStart = new BigDecimal(start).setScale(5, BigDecimal.ROUND_DOWN);

					float jiesuan = jiesuanStart.subtract(jiesuanEnd).setScale(5, BigDecimal.ROUND_DOWN).floatValue();

					if (jiesuan >= 0) {
						// win
						win++;
						outputList.add("出 - 买," + currentBean.toString() + ",盈" + ",1");

						MacdResultBean macdResultBean = new MacdResultBean("出 - 买", "盈", jiesuan, currentBean);
						resulList.add(macdResultBean);

					} else {
						lose++;
						outputList.add("出 - 买," + currentBean.toString() + ",亏" + ",-1");

						MacdResultBean macdResultBean = new MacdResultBean("出 - 买", "亏", jiesuan, currentBean);
						resulList.add(macdResultBean);
					}

					fuKui = fuKui + jiesuan;
					outputList.add("总浮亏," + fuKui + ",本次结算," + String.valueOf(jiesuan));

				}
				// 进场 买入
				start = currentBean.getClose();

				outputList.add("进 - 买," + currentBean.toString());
			}
			// fase 由 正 -> 负
			if ((currentBean.getDif() < 0 && before1Bean.getDif() == 0 && before2Bean.getDif() > 0)
					|| (currentBean.getDif() < 0 && before1Bean.getDif() > 0)) {

				if (start != 0) {
					// 已持有
					// 出场
					end = currentBean.getClose();

					BigDecimal jiesuanEnd = new BigDecimal(end).setScale(5, BigDecimal.ROUND_DOWN);
					BigDecimal jiesuanStart = new BigDecimal(start).setScale(5, BigDecimal.ROUND_DOWN);

					float jiesuan = jiesuanEnd.subtract(jiesuanStart).setScale(5, BigDecimal.ROUND_DOWN).floatValue();

					if (jiesuan >= 0) {
						// win
						win++;
						outputList.add("出 - 卖," + currentBean.toString() + ",盈" + ",1");

						MacdResultBean macdResultBean = new MacdResultBean("出 - 卖", "盈", jiesuan, currentBean);
						resulList.add(macdResultBean);
					} else {
						lose++;
						outputList.add("出 - 卖," + currentBean.toString() + ",亏" + ",-1");

						MacdResultBean macdResultBean = new MacdResultBean("出 - 卖", "亏", jiesuan, currentBean);
						resulList.add(macdResultBean);
					}

					fuKui = fuKui + jiesuan;
					outputList.add("总浮亏," + fuKui + ",本次结算," + String.valueOf(jiesuan));
				}
				// 进场 买入
				start = currentBean.getClose();

				outputList.add("进 - 卖," + currentBean.toString());
			}

		}

		outputList.add("win," + win);
		outputList.add("lose," + lose);

		BigDecimal winR = new BigDecimal(win).setScale(0, BigDecimal.ROUND_DOWN);
		BigDecimal totalR = new BigDecimal(win + lose).setScale(0, BigDecimal.ROUND_DOWN);
		float rate = winR.divide(totalR, 2, BigDecimal.ROUND_DOWN).floatValue();

		outputList.add("rate," + rate);
		outputList.add("fuKui," + fuKui);

		System.out.println("win:" + win);
		System.out.println("lose:" + lose);
		System.out.println("rate:" + rate);
		System.out.println("fuKui:" + fuKui);

		return outputList;
	}

	private MacdDataBean getNullBean() {

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

	public void calculateMacd(List<MacdDataBean> beanList, int fast, int slow, int signal) {

		for (int i = 0; i < beanList.size(); i++) {

			MacdDataBean beforeBean = null;
			if (i == 0) {
				beforeBean = getNullBean();
			} else {
				beforeBean = beanList.get(i - 1);
			}

			MacdDataBean currentBean = beanList.get(i);

			calculateMacd(beforeBean, currentBean, fast, slow, signal);
		}

	}

	public List<MacdDataBean> getInputData(String filepath) {

		File csv = new File(filepath); // CSV文件路径
		csv.setReadable(true);// 设置可读
		csv.setWritable(true);// 设置可写
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csv));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String line = "";
		List<MacdDataBean> list = new ArrayList<MacdDataBean>();

		try {
			while ((line = br.readLine()) != null) // 读取到的内容给line变量
			{

				String[] arrayData = line.split(",");

				MacdDataBean bean = new MacdDataBean();
				bean.setTimeYMDHM(arrayData[0].replace(".", ""));
				bean.setTimeH(arrayData[1].substring(0, 2));
				bean.setOpen(Float.valueOf(arrayData[2]));
				bean.setHigh(Float.valueOf(arrayData[3]));
				bean.setLow(Float.valueOf(arrayData[4]));
				bean.setClose(Float.valueOf(arrayData[5]));

				list.add(bean);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	private void calculateMacd(MacdDataBean beforeBean, MacdDataBean currentBean, final int fast, final int slow,
			final int bar) {

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

		float fastEma = beforeFastEma.add(close.subtract(beforeFastEma).multiply(int2).divide(int13, RoundingMode.HALF_DOWN)).floatValue();

		float slowEma = beforeSlowEma.add(close.subtract(beforeSlowEma).multiply(int2).divide(int27, RoundingMode.HALF_DOWN)).floatValue();

		currentBean.setFastEMA(fastEma);
		currentBean.setSlowEMA(slowEma);

		// DIFF=今日EMA（12）- 今日EMA（26）
		currentBean.setDif(fastEma - slowEma);

		BigDecimal int10 = new BigDecimal(bar + 1).setScale(0, BigDecimal.ROUND_DOWN);
		BigDecimal beforeDEA = new BigDecimal(beforeBean.getDea()).setScale(5, BigDecimal.ROUND_DOWN);
		BigDecimal currentDif = new BigDecimal(currentBean.getDif()).setScale(5, BigDecimal.ROUND_DOWN);
		// DEA（MACD）= 前一日DEA×8/10＋今日DIF×2/10
		float dea = beforeDEA.add(currentDif.subtract(beforeDEA).multiply(int2).divide(int10, RoundingMode.HALF_DOWN)).floatValue();

		currentBean.setDea(dea);
	}

	/**
	 * Calculate EMA,
	 * 
	 * @param list
	 *            :Price list to calculate，the first at head, the last at tail.
	 * @return
	 */
	public static final Double getEXPMA(final List<Double> list, final int number) {
		// 开始计算EMA值，
		Double k = 2.0 / (number + 1.0);// 计算出序数
		Double ema = list.get(0);// 第一天ema等于当天收盘价
		for (int i = 1; i < list.size(); i++) {
			// 第二天以后，当天收盘 收盘价乘以系数再加上昨天EMA乘以系数-1
			ema = list.get(i) * k + ema * (1 - k);
		}
		return ema;
	}

	/**
	 * calculate MACD values
	 * 
	 * @param list
	 *            :Price list to calculate，the first at head, the last at tail.
	 * @param shortPeriod
	 *            :the short period value.
	 * @param longPeriod
	 *            :the long period value.
	 * @param midPeriod
	 *            :the mid period value.
	 * @return
	 */
	public static final HashMap<String, Double> getMACD(final List<Double> list, final int shortPeriod,
			final int longPeriod, int midPeriod) {
		HashMap<String, Double> macdData = new HashMap<String, Double>();
		List<Double> diffList = new ArrayList<Double>();
		Double shortEMA = 0.0;
		Double longEMA = 0.0;
		Double dif = 0.0;
		Double dea = 0.0;

		for (int i = list.size() - 1; i >= 0; i--) {
			List<Double> sublist = list.subList(0, list.size() - i);
			shortEMA = getEXPMA(sublist, shortPeriod);
			longEMA = getEXPMA(sublist, longPeriod);
			dif = shortEMA - longEMA;
			diffList.add(dif);
		}
		dea = getEXPMA(diffList, midPeriod);
		macdData.put("DIF", dif);
		macdData.put("DEA", dea);
		macdData.put("MACD", (dif - dea) * 2);
		return macdData;
	}

}
