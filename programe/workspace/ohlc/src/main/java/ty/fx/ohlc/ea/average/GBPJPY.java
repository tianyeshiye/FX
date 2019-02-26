package ty.fx.ohlc.ea.average;

import java.util.List;

import ty.fx.macd.bean.MacdDataBean;
import ty.fx.utils.InputFileUtils;

public class GBPJPY {

	public static void main(String[] args) throws Exception {

		GBPJPY GBPJPY = new GBPJPY();

		GBPJPY.test();
	}

	public void test() {

		// DECIMAL_POINT = 3
		AverageValue instance = new AverageValue(3);
		List<MacdDataBean> beanList;
		float averageValue;

		// **********
		// ********** GBPJPYH1-12-h1
		System.out.println("----------------------------------------");
		System.out.println("GBPJPYH1-12-h1");

		beanList = InputFileUtils.getInputData("data/GBPJPYH1.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);

		// **********
		// ********** GBPJPYH4-12-h4
		System.out.println("----------------------------------------");
		System.out.println("GBPJPYH1-12-h4");

		beanList = InputFileUtils.getInputData("data/GBPJPYH4.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);

		// **********
		// ********** GBPJPYH4-12-D1
		System.out.println("----------------------------------------");
		System.out.println("GBPJPYH1-12-D1");
		beanList = InputFileUtils.getInputData("data/GBPJPYD1.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);
	}
}
