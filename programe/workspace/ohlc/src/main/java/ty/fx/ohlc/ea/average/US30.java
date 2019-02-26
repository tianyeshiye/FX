package ty.fx.ohlc.ea.average;

import java.util.List;

import ty.fx.macd.bean.MacdDataBean;
import ty.fx.utils.InputFileUtils;

public class US30 {

	public static void main(String[] args) throws Exception {

		US30 US30 = new US30();

		US30.test();
	}

	public void test() {

		// DECIMAL_POINT = 3
		AverageValue instance = new AverageValue(3);
		List<MacdDataBean> beanList;
		float averageValue;

		// **********
		// ********** US30H1-12-h1
		System.out.println("----------------------------------------");
		System.out.println("US30H1-12-h1");

		beanList = InputFileUtils.getInputData("data/US30H1.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);

		// **********
		// ********** US30H4-12-h4
		System.out.println("----------------------------------------");
		System.out.println("US30H1-12-h4");

		beanList = InputFileUtils.getInputData("data/US30H4.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);

		// **********
		// ********** US30H4-12-D1
		System.out.println("----------------------------------------");
		System.out.println("US30H1-12-D1");
		beanList = InputFileUtils.getInputData("data/US30D1.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);
	}
}
