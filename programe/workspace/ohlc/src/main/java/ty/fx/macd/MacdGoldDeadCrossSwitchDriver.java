package ty.fx.macd;

import java.util.ArrayList;
import java.util.List;

import ty.fx.bean.TimeDataBaseBean;
import ty.fx.macd.bean.MacdDataBean;
import ty.fx.macd.bean.MacdResultBean;
import ty.fx.macd.ea.MacdGoldDeadCrossSwitch;
import ty.fx.macd.ea.Macds;
import ty.fx.utils.ExcelFileUtils;
import ty.fx.utils.InputFileUtils;

public class MacdGoldDeadCrossSwitchDriver {

	public static void main(String[] args) throws Exception {

		MacdGoldDeadCrossSwitchDriver macdGoldDeadCrossSwitchDriver = new MacdGoldDeadCrossSwitchDriver();

		int zhiyingPoint = 10;
		// ********** USDJPYH1-12-h1 interval
//		// USDJPY
//		macdGoldDeadCrossSwitchDriver.execute("USDJPY", "H1", 3, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("USDJPY", "H4", 3, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("USDJPY", "D1", 3, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("USDJPY", "H1", 3, zhiyingPoint, 30, 65, 23);
//		macdGoldDeadCrossSwitchDriver.execute("USDJPY", "H4", 3, zhiyingPoint, 30, 65, 23);
//		macdGoldDeadCrossSwitchDriver.execute("USDJPY", "D1", 3, zhiyingPoint, 30, 65, 23);

//		// EURJPY
//		macdGoldDeadCrossSwitchDriver.execute("EURJPY", "H1", 3, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("EURJPY", "H4", 3, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("EURJPY", "D1", 3, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("EURJPY", "H1", 3, zhiyingPoint, 30, 65, 23);
//		macdGoldDeadCrossSwitchDriver.execute("EURJPY", "H4", 3, zhiyingPoint, 30, 65, 23);
//		macdGoldDeadCrossSwitchDriver.execute("EURJPY", "D1", 3, zhiyingPoint, 30, 65, 23);
//
//		// EURUSD
//		macdGoldDeadCrossSwitchDriver.execute("EURUSD", "H1", 5, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("EURUSD", "H4", 5, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("EURUSD", "D1", 5, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("EURUSD", "H1", 5, zhiyingPoint, 30, 65, 23);
//		macdGoldDeadCrossSwitchDriver.execute("EURUSD", "H4", 5, zhiyingPoint, 30, 65, 23);
//		macdGoldDeadCrossSwitchDriver.execute("EURUSD", "D1", 5, zhiyingPoint, 30, 65, 23);
//
//		zhiyingPoint = 30;
//		
//		// GBPJPY
//		macdGoldDeadCrossSwitchDriver.execute("GBPJPY", "H1", 3, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("GBPJPY", "H4", 3, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("GBPJPY", "D1", 3, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("GBPJPY", "H1", 3, zhiyingPoint, 30, 65, 23);
//		macdGoldDeadCrossSwitchDriver.execute("GBPJPY", "H4", 3, zhiyingPoint, 30, 65, 23);
//		macdGoldDeadCrossSwitchDriver.execute("GBPJPY", "D1", 3, zhiyingPoint, 30, 65, 23);
//
//		// GBPUSD
//		macdGoldDeadCrossSwitchDriver.execute("GBPUSD", "H1", 5, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("GBPUSD", "H4", 5, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("GBPUSD", "D1", 5, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("GBPUSD", "H1", 5, zhiyingPoint, 30, 65, 23);
//		macdGoldDeadCrossSwitchDriver.execute("GBPUSD", "H4", 5, zhiyingPoint, 30, 65, 23);
//		macdGoldDeadCrossSwitchDriver.execute("GBPUSD", "D1", 5, zhiyingPoint, 30, 65, 23);
//
//		zhiyingPoint = 500;
//		
//		// JP225
//		macdGoldDeadCrossSwitchDriver.execute("JP225", "H1", 2, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("JP225", "H4", 2, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("JP225", "D1", 2, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("JP225", "H1", 2, zhiyingPoint, 30, 65, 23);
//		macdGoldDeadCrossSwitchDriver.execute("JP225", "H4", 2, zhiyingPoint, 30, 65, 23);
//		macdGoldDeadCrossSwitchDriver.execute("JP225", "D1", 2, zhiyingPoint, 30, 65, 23);
		
		
		zhiyingPoint = 500;
		
		// XAUUSD   6位
//		macdGoldDeadCrossSwitchDriver.execute("XAUUSD", "H1", 2, zhiyingPoint, 12, 26, 9);
		macdGoldDeadCrossSwitchDriver.execute("XAUUSD", "H4", 2, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("XAUUSD", "D1", 2, zhiyingPoint, 12, 26, 9);
//		macdGoldDeadCrossSwitchDriver.execute("XAUUSD", "H1", 2, zhiyingPoint, 30, 65, 23);
//		macdGoldDeadCrossSwitchDriver.execute("XAUUSD", "H4", 2, zhiyingPoint, 30, 65, 23);
//		macdGoldDeadCrossSwitchDriver.execute("XAUUSD", "D1", 2, zhiyingPoint, 30, 65, 23);
	}

	private void execute(String currencyPair, String interval, int decimalPointPara, int  zhiyingPoint, int macdFast, int macdSlow,
			int macdColumn) {
		
		String zhiBiaoName = macdFast + "_" + macdSlow + "_" + macdColumn;

		// DECIMAL_POINT = 3
		MacdGoldDeadCrossSwitch instance = new MacdGoldDeadCrossSwitch(decimalPointPara, zhiyingPoint);

		List<String> logList = new ArrayList<String>();
		List<String> printList = new ArrayList<String>();
		List<String> printSortList = new ArrayList<String>();
		List<MacdDataBean> macdList = new ArrayList<MacdDataBean>();
		List<MacdResultBean> resulList = new ArrayList<MacdResultBean>();

		// ********** USDJPYH1-12-interval
		System.out.println("----------------------------------------");
		// System.out.println("USDJPYH1-12-h1");
		System.out.println(currencyPair + interval + "-" + zhiBiaoName + "-" + interval);

		// macdList = InputFileUtils.getInputData("data/USDJPYH1.csv");
		List<TimeDataBaseBean> timeDataBaseBeanList = InputFileUtils.getInputData("data/" + currencyPair + interval + ".csv");
		macdList = Macds.calculateMacd(timeDataBaseBeanList, macdFast, macdSlow, macdColumn);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		// ExcelFileUtils.writeExcel(printList, "output/USDJPY.xlsx", "H1-12");
		ExcelFileUtils.writeExcel(printList, "output/GoldDeadCross/" + currencyPair + ".xlsx",
				interval + "-" + zhiBiaoName);
		// ExcelFileUtils.writeExcel(printSortList, "output/USDJPY.xlsx",
		// "H1-sort-12");
		ExcelFileUtils.writeExcel(printSortList, "output/GoldDeadCross/" + currencyPair + ".xlsx",
				interval + "-sort-" + zhiBiaoName);
		// ExcelFileUtils.writeExcel(logList, "output/USDJPY.xlsx",
		// "H1-log-12");
		ExcelFileUtils.writeExcel(logList, "output/GoldDeadCross/" + currencyPair + ".xlsx",
				interval + "-log-" + zhiBiaoName);
	}
}
