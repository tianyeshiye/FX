package ty.fx.ohlc.ea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import ty.fx.ohlc.bean.ResultBean;
import ty.fx.ohlc.bean.TimeDataBean;

public class PersentCalculate {

	public static void main(String[] args) throws Exception {

		PersentCalculate calculate = new PersentCalculate();

		float floatValue;

		// List<TimeDataBean> inputdataList =
		// calculate.getInputData("data/GBPUSDH1.csv");
		//
		// // GBPUSD
		// floatValue = 0.005f;
		// System.out.println("GBPUSD = " + floatValue);
		// calculate.calculatePersent(inputdataList, floatValue);

		// List<TimeDataBean> inputdataList =
		// calculate.getInputData("data/GBPJPYH1.csv");
		//
		// // GBPUSD
		// floatValue = 0.2f;
		// System.out.println("GBPJPY = " + floatValue);
		// calculate.calculatePersent(inputdataList, floatValue);

		// List<TimeDataBean> inputdataList =
		// calculate.getInputData("data/JP225H1.csv");
		//
		// // GBPUSD
		// floatValue = 110f;
		// System.out.println("JP225 = " + floatValue);
		// calculate.calculatePersent(inputdataList, floatValue);

		List<TimeDataBean> inputdataList = calculate.getInputData("data/EURUSDH1.csv");

		// GBPUSD
		floatValue = 0.004f;
		System.out.println("EURUSD = " + floatValue);
		calculate.calculatePersent(inputdataList, floatValue);

		calculate.average(inputdataList);
	}

	private List<TimeDataBean> getInputData(String filepath) {

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
		List<TimeDataBean> list = new ArrayList<TimeDataBean>();

		try {
			while ((line = br.readLine()) != null) // 读取到的内容给line变量
			{

				String[] arrayData = line.split(",");

				TimeDataBean bean = new TimeDataBean();
				bean.setTimeYMDHM(arrayData[0].replace(".", ""));
				bean.setTimeH(arrayData[1].substring(0, 2));
				bean.setOpen(Float.valueOf(arrayData[2]));
				bean.setHigh(Float.valueOf(arrayData[3]));
				bean.setLow(Float.valueOf(arrayData[4]));
				bean.setClose(Float.valueOf(arrayData[5]));

				BigDecimal h = new BigDecimal(bean.getHigh()).setScale(5, BigDecimal.ROUND_DOWN);
				BigDecimal l = new BigDecimal(bean.getLow()).setScale(5, BigDecimal.ROUND_DOWN);
				float hl = h.subtract(l).floatValue();

				bean.setHlValue(hl);

				BigDecimal o = new BigDecimal(bean.getOpen()).setScale(5, BigDecimal.ROUND_DOWN);
				BigDecimal c = new BigDecimal(bean.getClose()).setScale(5, BigDecimal.ROUND_DOWN);
				float oc = o.subtract(c).floatValue();

				bean.setOcValue(oc);

				list.add(bean);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	private void average(List<TimeDataBean> inputdataList) {

		Map<String, List<TimeDataBean>> groupByList = inputdataList.stream()
				.collect(Collectors.groupingBy(TimeDataBean::getTimeH));

		List<ResultBean> resultBeanList = new ArrayList<ResultBean>();

		groupByList.forEach((time, beanList) -> {

			ResultBean resultBean = new ResultBean();

			resultBean.setTime(time);
			resultBean.setAverageV(getAverageValue(beanList));

			resultBeanList.add(resultBean);
		});

		AverageComparator compareAverage = new AverageComparator();
		Collections.sort(resultBeanList, compareAverage);

		resultBeanList.forEach(resultBean -> {
			System.out.println(resultBean.getTime() + "," + resultBean.getAverageV());
		});
	}

	private void calculatePersent(List<TimeDataBean> inputdataList, float minFloat) {

		List<TimeDataBean> filterList = new ArrayList<TimeDataBean>();

		inputdataList.forEach(bean -> {
			if (Math.abs(bean.getHlValue()) >= minFloat) {
				filterList.add(bean);
			}
		});

		int total = filterList.size();

		Map<String, List<TimeDataBean>> groupByList = filterList.stream()
				.collect(Collectors.groupingBy(TimeDataBean::getTimeH));

		List<ResultBean> resultBeanList = new ArrayList<ResultBean>();

		groupByList.forEach((time, beanList) -> {

			ResultBean resultBean = new ResultBean();

			HighLowComparator compareHightLow = new HighLowComparator();
			Collections.sort(beanList, compareHightLow);

			int size = beanList.size();
			resultBean.setTime(time);
			resultBean.setMaxValue(beanList.get(0).getHlValue());
			resultBean.setMinVale(beanList.get(size - 1).getHlValue());
			resultBean.setCount(size);
			resultBean.setPersent(present(size, total));

			resultBeanList.add(resultBean);
		});

		Collections.sort(resultBeanList, new Comparator<ResultBean>() {

			@Override
			public int compare(ResultBean bean1, ResultBean bean2) {
				double diff = bean1.getCount() - bean2.getCount();
				if (diff > 0) {
					return -1;
				} else if (diff < 0) {
					return 1;
				}
				return 0;
			}
		});

		System.out.println(inputdataList.size());
		System.out.println(filterList.size() + " ---  " + present(filterList.size(), inputdataList.size()));

		resultBeanList.forEach(resultBean -> {
			System.out.println(resultBean.getTime() + "," + resultBean.getPersent() + "," + resultBean.getCount() + ","
					+ resultBean.getMaxValue() + "," + resultBean.getMinVale());
		});

	}

	public static String present(double num1, double num2) {
		double ratio = num1 / num2;
		NumberFormat format = NumberFormat.getPercentInstance();
		format.setMaximumFractionDigits(2);
		return format.format(ratio);
	}

	public float getAverageValue(List<TimeDataBean> inputDataList) {

		float sum = 0f;

		Double averageValue = inputDataList.stream().map(new Function<TimeDataBean, Float>() {
			@Override
			public Float apply(TimeDataBean t) {
				return Math.abs(t.getHlValue());
			}
		}).collect(Collectors.averagingDouble(x -> x));

		return averageValue.floatValue();
	}

	public class HighLowComparator implements Comparator<TimeDataBean> {

		@Override
		public int compare(TimeDataBean bean1, TimeDataBean bean2) {
			double diff = Math.abs(bean1.getHlValue()) - Math.abs(bean2.getHlValue());
			if (diff > 0) {
				return -1;
			} else if (diff < 0) {
				return 1;
			}
			return 0;
		}
	}

	public class StartEndComparator implements Comparator<TimeDataBean> {

		@Override
		public int compare(TimeDataBean bean1, TimeDataBean bean2) {
			double diff = Math.abs(bean1.getOcValue()) - Math.abs(bean2.getOcValue());
			if (diff > 0) {
				return -1;
			} else if (diff < 0) {
				return 1;
			}
			return 0;
		}
	}

	public class AverageComparator implements Comparator<ResultBean> {

		@Override
		public int compare(ResultBean bean1, ResultBean bean2) {
			double diff = Math.abs(bean1.getAverageV()) - Math.abs(bean2.getAverageV());
			if (diff > 0) {
				return -1;
			} else if (diff < 0) {
				return 1;
			}
			return 0;
		}
	}

}
