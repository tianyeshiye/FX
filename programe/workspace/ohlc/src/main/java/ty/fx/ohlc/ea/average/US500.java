package ty.fx.ohlc.ea.average;

import java.util.List;

import ty.fx.bean.TimeDataBaseBean;
import ty.fx.utils.InputFileUtils;

public class US500 {

	public static void main(String[] args) throws Exception {

		US500 US500 = new US500();

		US500.test();
	}

	public void test() {

		// DECIMAL_POINT = 3
		AverageValue instance = new AverageValue(3);
		List<TimeDataBaseBean> beanList;
		float averageValue;

		// **********
		// ********** US500H1-12-h1
		System.out.println("----------------------------------------");
		System.out.println("US500H1-12-h1");

		beanList = InputFileUtils.getInputData("data/US500H1.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);

		// **********
		// ********** US500H4-12-h4
		System.out.println("----------------------------------------");
		System.out.println("US500H1-12-h4");

		beanList = InputFileUtils.getInputData("data/US500H4.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);

		// **********
		// ********** US500H4-12-D1
		System.out.println("----------------------------------------");
		System.out.println("US500H1-12-D1");
		beanList = InputFileUtils.getInputData("data/US500D1.csv");

		averageValue = instance.ea(beanList);
		System.out.println(averageValue);
	}
}
