package ty.fx.macd.paramenttest;

import java.util.ArrayList;
import java.util.List;

import ty.fx.bean.TimeDataBaseBean;
import ty.fx.macd.bean.MacdDataBean;
import ty.fx.macd.bean.MacdResultBean;
import ty.fx.macd.ea.Macds;
import ty.fx.utils.ExcelFileUtils;
import ty.fx.utils.InputFileUtils;

public class MacdParamentTestDriver {

	public static void main(String[] args) throws Exception {

		MacdParamentTestDriver driver = new MacdParamentTestDriver();

		int zhiyingPoint = 100;
		
//		// EURUSD
		driver.execute("EURUSD", "H4", 5, zhiyingPoint, 12, 26, 9);
		driver.execute("EURUSD", "H4", 5, zhiyingPoint, 30, 65, 23);


	}

	private void execute(String currencyPair, String interval, int decimalPointPara, int  zhiyingPoint, int macdFast, int macdSlow,
			int macdColumn) {

		// DECIMAL_POINT = 3
		MacdParamentTest instance = new MacdParamentTest(decimalPointPara, zhiyingPoint);

		List<String> logList = new ArrayList<String>();
		List<String> printList = new ArrayList<String>();
		List<String> printSortList = new ArrayList<String>();
		List<MacdDataBean> macdList = new ArrayList<MacdDataBean>();
		List<MacdResultBean> resulList = new ArrayList<MacdResultBean>();

		// ********** USDJPYH1-12-interval
		System.out.println("----------------------------------------");
		// System.out.println("USDJPYH1-12-h1");
		System.out.println(currencyPair + interval + "-" + macdFast + "-" + interval);

		// macdList = InputFileUtils.getInputData("data/USDJPYH1.csv");
		List<TimeDataBaseBean> timeDataBaseBeanList = InputFileUtils.getInputData("data/" + currencyPair + interval + ".csv");
		macdList = Macds.calculateMacd(timeDataBaseBeanList, macdFast, macdSlow, macdColumn);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		// ExcelFileUtils.writeExcel(printList, "output/USDJPY.xlsx", "H1-12");
		ExcelFileUtils.writeExcel(printList, "output/GoldDeadStop/" + currencyPair + ".xlsx",
				interval + "-" + macdFast);
		// ExcelFileUtils.writeExcel(printSortList, "output/USDJPY.xlsx",
		// "H1-sort-12");
		ExcelFileUtils.writeExcel(printSortList, "output/GoldDeadStop/" + currencyPair + ".xlsx",
				interval + "-sort-" + macdFast);
		// ExcelFileUtils.writeExcel(logList, "output/USDJPY.xlsx",
		// "H1-log-12");
		ExcelFileUtils.writeExcel(logList, "output/GoldDeadStop/" + currencyPair + ".xlsx",
				interval + "-log-" + macdFast);
	}
}
