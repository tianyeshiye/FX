package ty.fx.ohlc.ea.average;

import java.util.List;

import ty.fx.macd.bean.MacdDataBean;
import ty.fx.utils.InputFileUtils;

public class GBPUSD {

	public static void main(String[] args) throws Exception {

		GBPUSD GBPUSD = new GBPUSD();

		GBPUSD.test();
	}

	public void test() {

		// DECIMAL_POINT = 3
		AverageValue instance = new AverageValue(3);
		List<MacdDataBean> beanList;
		float averageValue;

		// **********
		// ********** GBPUSDH1-12-h1
		System.out.println("----------------------------------------");
		System.out.println("GBPUSDH1-12-h1");

		beanList = InputFileUtils.getInputData("data/GBPUSDH1.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);

		// **********
		// ********** GBPUSDH4-12-h4
		System.out.println("----------------------------------------");
		System.out.println("GBPUSDH1-12-h4");

		beanList = InputFileUtils.getInputData("data/GBPUSDH4.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);

		// **********
		// ********** GBPUSDH4-12-D1
		System.out.println("----------------------------------------");
		System.out.println("GBPUSDH1-12-D1");
		beanList = InputFileUtils.getInputData("data/GBPUSDD1.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);
	}
}
