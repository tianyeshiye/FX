package ty.fx.macd;

import java.util.ArrayList;
import java.util.List;

import ty.fx.macd.bean.MacdDataBean;
import ty.fx.macd.bean.MacdResultBean;
import ty.fx.macd.ea.MacdPostiveNegativeSwitch;
import ty.fx.macd.ea.Macds;
import ty.fx.utils.ExcelFileUtils;
import ty.fx.utils.InputFileUtils;

public class MacdPostiveNegativeSwitchDriver {

	public static void main(String[] args) throws Exception {

		MacdPostiveNegativeSwitchDriver macdPostiveNegativeSwitch = new MacdPostiveNegativeSwitchDriver();

		int zhiyingPoint = 10;
		
		// ********** USDJPYH1-12-h1 interval
//		// USDJPY
//		macdPostiveNegativeSwitch.execute("USDJPY", "H1", 3, zhiyingPoint, 12, 26, 9);
//		macdPostiveNegativeSwitch.execute("USDJPY", "H4", 3, zhiyingPoint, 12, 26, 9);
//		macdPostiveNegativeSwitch.execute("USDJPY", "D1", 3, zhiyingPoint, 12, 26, 9);
//		macdPostiveNegativeSwitch.execute("USDJPY", "H1", 3, zhiyingPoint, 30, 65, 23);
//		macdPostiveNegativeSwitch.execute("USDJPY", "H4", 3, zhiyingPoint, 30, 65, 23);
//		macdPostiveNegativeSwitch.execute("USDJPY", "D1", 3, zhiyingPoint, 30, 65, 23);

//		// EURJPY
//		macdPostiveNegativeSwitch.execute("EURJPY", "H1", 3, zhiyingPoint, 12, 26, 9);
//		macdPostiveNegativeSwitch.execute("EURJPY", "H4", 3, zhiyingPoint, 12, 26, 9);
//		macdPostiveNegativeSwitch.execute("EURJPY", "D1", 3, zhiyingPoint, 12, 26, 9);
//		macdPostiveNegativeSwitch.execute("EURJPY", "H1", 3, zhiyingPoint, 30, 65, 23);
//		macdPostiveNegativeSwitch.execute("EURJPY", "H4", 3, zhiyingPoint, 30, 65, 23);
//		macdPostiveNegativeSwitch.execute("EURJPY", "D1", 3, zhiyingPoint, 30, 65, 23);
//
//		// EURUSD
//		macdPostiveNegativeSwitch.execute("EURUSD", "H1", 5, zhiyingPoint, 12, 26, 9);
//		macdPostiveNegativeSwitch.execute("EURUSD", "H4", 5, zhiyingPoint, 12, 26, 9);
//		macdPostiveNegativeSwitch.execute("EURUSD", "D1", 5, zhiyingPoint, 12, 26, 9);
//		macdPostiveNegativeSwitch.execute("EURUSD", "H1", 5, zhiyingPoint, 30, 65, 23);
//		macdPostiveNegativeSwitch.execute("EURUSD", "H4", 5, zhiyingPoint, 30, 65, 23);
//		macdPostiveNegativeSwitch.execute("EURUSD", "D1", 5, zhiyingPoint, 30, 65, 23);

		zhiyingPoint = 30;
		
		// GBPJPY
		macdPostiveNegativeSwitch.execute("GBPJPY", "H1", 3, zhiyingPoint, 12, 26, 9);
		macdPostiveNegativeSwitch.execute("GBPJPY", "H4", 3, zhiyingPoint, 12, 26, 9);
		macdPostiveNegativeSwitch.execute("GBPJPY", "D1", 3, zhiyingPoint, 12, 26, 9);
		macdPostiveNegativeSwitch.execute("GBPJPY", "H1", 3, zhiyingPoint, 30, 65, 23);
		macdPostiveNegativeSwitch.execute("GBPJPY", "H4", 3, zhiyingPoint, 30, 65, 23);
		macdPostiveNegativeSwitch.execute("GBPJPY", "D1", 3, zhiyingPoint, 30, 65, 23);
//
//		// GBPUSD
//		macdPostiveNegativeSwitch.execute("GBPUSD", "H1", 5, zhiyingPoint, 12, 26, 9);
//		macdPostiveNegativeSwitch.execute("GBPUSD", "H4", 5, zhiyingPoint, 12, 26, 9);
//		macdPostiveNegativeSwitch.execute("GBPUSD", "D1", 5, zhiyingPoint, 12, 26, 9);
//		macdPostiveNegativeSwitch.execute("GBPUSD", "H1", 5, zhiyingPoint, 30, 65, 23);
//		macdPostiveNegativeSwitch.execute("GBPUSD", "H4", 5, zhiyingPoint, 30, 65, 23);
//		macdPostiveNegativeSwitch.execute("GBPUSD", "D1", 5, zhiyingPoint, 30, 65, 23);
//
		zhiyingPoint = 500;
		// JP225
		macdPostiveNegativeSwitch.execute("JP225", "H1", 2, zhiyingPoint, 12, 26, 9);
		macdPostiveNegativeSwitch.execute("JP225", "H4", 2, zhiyingPoint, 12, 26, 9);
		macdPostiveNegativeSwitch.execute("JP225", "D1", 2, zhiyingPoint, 12, 26, 9);
		macdPostiveNegativeSwitch.execute("JP225", "H1", 2, zhiyingPoint, 30, 65, 23);
		macdPostiveNegativeSwitch.execute("JP225", "H4", 2, zhiyingPoint, 30, 65, 23);
		macdPostiveNegativeSwitch.execute("JP225", "D1", 2, zhiyingPoint, 30, 65, 23);
	}

	private void execute(String currencyPair, String interval, int decimalPointPara, int  zhiyingPoint, int macdFast, int macdSlow,
			int macdColumn) {

		// DECIMAL_POINT = 3
		MacdPostiveNegativeSwitch instance = new MacdPostiveNegativeSwitch(decimalPointPara, zhiyingPoint);

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
		macdList = InputFileUtils.getInputData("data/" + currencyPair + interval + ".csv");
		Macds.calculateMacd(macdList, macdFast, macdSlow, macdColumn);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		// ExcelFileUtils.writeExcel(printList, "output/USDJPY.xlsx", "H1-12");
		ExcelFileUtils.writeExcel(printList, "output/PostiveNegative/macd-" + currencyPair + ".xlsx",
				interval + "-" + macdFast);
		// ExcelFileUtils.writeExcel(printSortList, "output/USDJPY.xlsx",
		// "H1-sort-12");
		ExcelFileUtils.writeExcel(printSortList, "output/PostiveNegative/macd-" + currencyPair + ".xlsx",
				interval + "-sort-" + macdFast);
		// ExcelFileUtils.writeExcel(logList, "output/USDJPY.xlsx",
		// "H1-log-12");
		ExcelFileUtils.writeExcel(logList, "output/PostiveNegative/macd-" + currencyPair + ".xlsx",
				interval + "-log-" + macdFast);
	}
}
