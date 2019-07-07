package ty.fx.movingaverage;

import java.util.ArrayList;
import java.util.List;

import ty.fx.bean.TimeDataBaseBean;
import ty.fx.movingaverage.bean.MovingAverBean;
import ty.fx.movingaverage.bean.MovingResultBean;
import ty.fx.movingaverage.ea.MovingAverEa;
import ty.fx.movingaverage.ea.MovingAvers;
import ty.fx.utils.ExcelFileUtils;
import ty.fx.utils.InputFileUtils;

public class MovingAverDriver {

	public static void main(String[] args) throws Exception {

		MovingAverDriver movingAverDriver = new MovingAverDriver();

		int zhiyingPoint;

		// XAUUSD 6位
		zhiyingPoint = 500;

//		movingAverDriver.execute("XAUUSD", "H1", 2, zhiyingPoint, 15, 50);
		movingAverDriver.execute("XAUUSD", "H4", 2, zhiyingPoint, 15, 50);
//		movingAverDriver.execute("XAUUSD", "D1", 2, zhiyingPoint, 15, 50);
//		movingAverDriver.execute("XAUUSD", "H1", 2, zhiyingPoint, 21, 75);
//		movingAverDriver.execute("XAUUSD", "H4", 2, zhiyingPoint, 21, 75);
//		movingAverDriver.execute("XAUUSD", "D1", 2, zhiyingPoint, 21, 75);
	}

	private void execute(String currencyPair, String interval, int decimalPointPara, int zhiyingPoint, int fast,
			int slow) {

		// DECIMAL_POINT = 3
		MovingAverEa instance = new MovingAverEa(decimalPointPara, zhiyingPoint);

		List<String> logList = new ArrayList<String>();
		List<String> printList = new ArrayList<String>();
		List<String> printSortList = new ArrayList<String>();
		List<TimeDataBaseBean> baseDataList = new ArrayList<TimeDataBaseBean>();
		List<MovingResultBean> resulList = new ArrayList<MovingResultBean>();

		// ********** USDJPYH1-12-interval
		System.out.println("----------------------------------------");
		// System.out.println("USDJPYH1-12-h1");
		System.out.println(currencyPair + interval + "-" + fast + "-" + interval);

		// macdList = InputFileUtils.getInputData("data/USDJPYH1.csv");
		baseDataList = InputFileUtils.getInputData("data/" + currencyPair + interval + ".csv");
		List<MovingAverBean> movingAverBeanList = MovingAvers.calculate(baseDataList, fast, slow, decimalPointPara);

		logList = instance.ea(movingAverBeanList, resulList);
		printList = MovingAvers.printResult(resulList);
		printSortList = MovingAvers.printSortResult(resulList);

		// ExcelFileUtils.writeExcel(printList, "output/USDJPY.xlsx", "H1-12");
		ExcelFileUtils.writeExcel(printList, "output/moving/" + currencyPair + "_moving.xlsx", interval + "-" + fast);
		// ExcelFileUtils.writeExcel(printSortList, "output/USDJPY.xlsx",
		// "H1-sort-12");
		ExcelFileUtils.writeExcel(printSortList, "output/moving/" + currencyPair + "_moving.xlsx",
				interval + "-sort-" + fast);
		// ExcelFileUtils.writeExcel(logList, "output/USDJPY.xlsx",
		// "H1-log-12");
		ExcelFileUtils.writeExcel(logList, "output/moving/" + currencyPair + "_moving.xlsx", interval + "-log-" + fast);
	}
}
