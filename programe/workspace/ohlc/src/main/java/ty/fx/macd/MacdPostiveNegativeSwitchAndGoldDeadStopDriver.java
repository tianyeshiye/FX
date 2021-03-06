package ty.fx.macd;

import java.util.ArrayList;
import java.util.List;

import ty.fx.bean.TimeDataBaseBean;
import ty.fx.macd.bean.MacdDataBean;
import ty.fx.macd.bean.MacdResultBean;
import ty.fx.macd.ea.MacdPostiveNegativeSwitchAndGoldDeadStop;
import ty.fx.macd.ea.Macds;
import ty.fx.utils.ExcelFileUtils;
import ty.fx.utils.InputFileUtils;

public class MacdPostiveNegativeSwitchAndGoldDeadStopDriver {

	public static void main(String[] args) throws Exception {

		MacdPostiveNegativeSwitchAndGoldDeadStopDriver driver = new MacdPostiveNegativeSwitchAndGoldDeadStopDriver();

		int zhiyingPoint = 10;
		
		// ********** USDJPYH1-12-h1 interval
//		// USDJPY
//		driver.execute("USDJPY", "H1", 3, zhiyingPoint, 12, 26, 9);
//		driver.execute("USDJPY", "H4", 3, zhiyingPoint, 12, 26, 9);
//		driver.execute("USDJPY", "D1", 3, zhiyingPoint, 12, 26, 9);
//		driver.execute("USDJPY", "H1", 3, zhiyingPoint, 30, 65, 23);
//		driver.execute("USDJPY", "H4", 3, zhiyingPoint, 30, 65, 23);
//		driver.execute("USDJPY", "D1", 3, zhiyingPoint, 30, 65, 23);

//		// EURJPY
//		driver.execute("EURJPY", "H1", 3, zhiyingPoint, 12, 26, 9);
//		driver.execute("EURJPY", "H4", 3, zhiyingPoint, 12, 26, 9);
//		driver.execute("EURJPY", "D1", 3, zhiyingPoint, 12, 26, 9);
//		driver.execute("EURJPY", "H1", 3, zhiyingPoint, 30, 65, 23);
//		driver.execute("EURJPY", "H4", 3, zhiyingPoint, 30, 65, 23);
//		driver.execute("EURJPY", "D1", 3, zhiyingPoint, 30, 65, 23);
//
//		// EURUSD
//		driver.execute("EURUSD", "H1", 5, zhiyingPoint, 12, 26, 9);
//		driver.execute("EURUSD", "H4", 5, zhiyingPoint, 12, 26, 9);
//		driver.execute("EURUSD", "D1", 5, zhiyingPoint, 12, 26, 9);
//		driver.execute("EURUSD", "H1", 5, zhiyingPoint, 30, 65, 23);
//		driver.execute("EURUSD", "H4", 5, zhiyingPoint, 30, 65, 23);
//		driver.execute("EURUSD", "D1", 5, zhiyingPoint, 30, 65, 23);

//		zhiyingPoint = 30;
//		
//		// GBPJPY
//		driver.execute("GBPJPY", "H1", 3, zhiyingPoint, 12, 26, 9);
//		driver.execute("GBPJPY", "H4", 3, zhiyingPoint, 12, 26, 9);
//		driver.execute("GBPJPY", "D1", 3, zhiyingPoint, 12, 26, 9);
//		driver.execute("GBPJPY", "H1", 3, zhiyingPoint, 30, 65, 23);
//		driver.execute("GBPJPY", "H4", 3, zhiyingPoint, 30, 65, 23);
//		driver.execute("GBPJPY", "D1", 3, zhiyingPoint, 30, 65, 23);
//
//		// GBPUSD
//		driver.execute("GBPUSD", "H1", 5, zhiyingPoint, 12, 26, 9);
//		driver.execute("GBPUSD", "H4", 5, zhiyingPoint, 12, 26, 9);
//		driver.execute("GBPUSD", "D1", 5, zhiyingPoint, 12, 26, 9);
//		driver.execute("GBPUSD", "H1", 5, zhiyingPoint, 30, 65, 23);
//		driver.execute("GBPUSD", "H4", 5, zhiyingPoint, 30, 65, 23);
//		driver.execute("GBPUSD", "D1", 5, zhiyingPoint, 30, 65, 23);
//
		zhiyingPoint = 5000;
		// JP225
		driver.execute("JP225", "H1", 1, zhiyingPoint, 12, 26, 9);
		driver.execute("JP225", "H4", 1, zhiyingPoint, 12, 26, 9);
		driver.execute("JP225", "D1", 1, zhiyingPoint, 12, 26, 9);
		driver.execute("JP225", "H1", 1, zhiyingPoint, 30, 65, 23);
		driver.execute("JP225", "H4", 1, zhiyingPoint, 30, 65, 23);
		driver.execute("JP225", "D1", 1, zhiyingPoint, 30, 65, 23);
	}

	private void execute(String currencyPair, String interval, int decimalPointPara, int  zhiyingPoint, int macdFast, int macdSlow,
			int macdColumn) {

		// DECIMAL_POINT = 3
		MacdPostiveNegativeSwitchAndGoldDeadStop instance = new MacdPostiveNegativeSwitchAndGoldDeadStop(decimalPointPara, zhiyingPoint);

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
