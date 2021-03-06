package ty.fx.macd;

import java.util.ArrayList;
import java.util.List;

import ty.fx.bean.TimeDataBaseBean;
import ty.fx.macd.bean.MacdDataBean;
import ty.fx.macd.bean.MacdResultBean;
import ty.fx.macd.ea.MacdWithStop;
import ty.fx.macd.ea.Macds;
import ty.fx.utils.ExcelFileUtils;
import ty.fx.utils.InputFileUtils;

public class GBPJPYMacdStop {

	public static void main(String[] args) throws Exception {

		GBPJPYMacdStop GBPJPY = new GBPJPYMacdStop();

		GBPJPY.test();
	}

	public void test() {

		// DECIMAL_POINT = 3
		// stop point =
		MacdWithStop instance = new MacdWithStop(3, 4f);

		List<String> logList;
		List<String> printList;
		List<String> printSortList;
		List<MacdDataBean> macdList;
		List<MacdResultBean> resulList;

		// instance.calculateMacd(macdList, 30, 65, 23);
		// **********
		// ********** GBPJPYH1-12-h1
		System.out.println("----------------------------------------");
		System.out.println("GBPJPYH1-12-h1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		List<TimeDataBaseBean> timeDataBaseBeanList = InputFileUtils.getInputData("data/GBPJPYH1.csv");
		macdList = Macds.calculateMacd(timeDataBaseBeanList, 12, 26, 9);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/GBPJPYMacdStop.xlsx", "H1-12");
		ExcelFileUtils.writeExcel(printSortList, "output/GBPJPYMacdStop.xlsx", "H1-sort-12");
		ExcelFileUtils.writeExcel(logList, "output/GBPJPYMacdStop.xlsx", "H1-log-12");

		// **********
		// ********** GBPJPYH4-12-h4
		System.out.println("----------------------------------------");
		System.out.println("GBPJPYH1-12-h4");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		timeDataBaseBeanList = InputFileUtils.getInputData("data/GBPJPYH4.csv");
		macdList = Macds.calculateMacd(timeDataBaseBeanList, 12, 26, 9);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/GBPJPYMacdStop.xlsx", "H4-12");
		ExcelFileUtils.writeExcel(printSortList, "output/GBPJPYMacdStop.xlsx", "H4-sort-12");
		ExcelFileUtils.writeExcel(logList, "output/GBPJPYMacdStop.xlsx", "H4-log-12");

		// **********
		// ********** GBPJPYH4-12-D1
		System.out.println("----------------------------------------");
		System.out.println("GBPJPYH1-12-D1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		timeDataBaseBeanList = InputFileUtils.getInputData("data/GBPJPYD1.csv");
		macdList = Macds.calculateMacd(timeDataBaseBeanList, 12, 26, 9);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/GBPJPYMacdStop.xlsx", "D1-12");
		ExcelFileUtils.writeExcel(printSortList, "output/GBPJPYMacdStop.xlsx", "D1-sort-12");
		ExcelFileUtils.writeExcel(logList, "output/GBPJPYMacdStop.xlsx", "D1-log-12");

		// **********
		// ********** GBPJPYH1-30-h1
		System.out.println("----------------------------------------");
		System.out.println("GBPJPYH1-30-h1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		timeDataBaseBeanList = InputFileUtils.getInputData("data/GBPJPYH1.csv");
		macdList = Macds.calculateMacd(timeDataBaseBeanList, 30, 65, 23);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/GBPJPYMacdStop.xlsx", "H1-30");
		ExcelFileUtils.writeExcel(printSortList, "output/GBPJPYMacdStop.xlsx", "H1-sort-30");
		ExcelFileUtils.writeExcel(logList, "output/GBPJPYMacdStop.xlsx", "H1-log-30");

		// **********
		// ********** GBPJPYH4-30-h4
		System.out.println("----------------------------------------");
		System.out.println("GBPJPYH1-30-h4");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		timeDataBaseBeanList = InputFileUtils.getInputData("data/GBPJPYH4.csv");
		macdList = Macds.calculateMacd(timeDataBaseBeanList, 30, 65, 23);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/GBPJPYMacdStop.xlsx", "H4-30");
		ExcelFileUtils.writeExcel(printSortList, "output/GBPJPYMacdStop.xlsx", "H4-sort-30");
		ExcelFileUtils.writeExcel(logList, "output/GBPJPYMacdStop.xlsx", "H4-log-30");

		// **********
		// ********** GBPJPYH4-30-D1
		System.out.println("----------------------------------------");
		System.out.println("GBPJPYH1-30-D1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		timeDataBaseBeanList = InputFileUtils.getInputData("data/GBPJPYD1.csv");
		macdList = Macds.calculateMacd(timeDataBaseBeanList, 30, 65, 23);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/GBPJPYMacdStop.xlsx", "D1-30");
		ExcelFileUtils.writeExcel(printSortList, "output/GBPJPYMacdStop.xlsx", "D1-sort-30");
		ExcelFileUtils.writeExcel(logList, "output/GBPJPYMacdStop.xlsx", "D1-log-30");
	}
}
