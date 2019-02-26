package ty.fx.ohlc.ea.average;

import java.util.List;

import ty.fx.macd.bean.MacdDataBean;
import ty.fx.utils.InputFileUtils;

public class JP225 {

	public static void main(String[] args) throws Exception {

		JP225 JP225 = new JP225();

		JP225.test();
	}

	public void test() {

		// DECIMAL_POINT = 3
		AverageValue instance = new AverageValue(3);
		List<MacdDataBean> beanList;
		float averageValue;

		// **********
		// ********** JP225H1-12-h1
		System.out.println("----------------------------------------");
		System.out.println("JP225H1-12-h1");

		beanList = InputFileUtils.getInputData("data/JP225H1.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);

		// **********
		// ********** JP225H4-12-h4
		System.out.println("----------------------------------------");
		System.out.println("JP225H1-12-h4");

		beanList = InputFileUtils.getInputData("data/JP225H4.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);

		// **********
		// ********** JP225H4-12-D1
		System.out.println("----------------------------------------");
		System.out.println("JP225H1-12-D1");
		beanList = InputFileUtils.getInputData("data/JP225D1.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);
	}
}
