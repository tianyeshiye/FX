package ty.fx.macd;

import java.util.ArrayList;
import java.util.List;

import ty.fx.macd.ea.Macd;
import ty.fx.utils.ExcelFileUtils;

public class GBPJPY {

	public static void main(String[] args) throws Exception {
		
		GBPJPY GBPJPY = new GBPJPY();

		GBPJPY.test();
	}

	public void test() {

		// DECIMAL_POINT = 3
		Macd instance = new Macd(3);

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

		macdList = Macds.getInputData("data/GBPJPYH1.csv");
		Macds.calculateMacd(macdList, 12, 26, 9);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/GBPJPY.xlsx", "GBPJPYH1-12");
		ExcelFileUtils.writeExcel(printSortList, "output/GBPJPY.xlsx", "GBPJPYH1-sort-12");
		ExcelFileUtils.writeExcel(logList, "output/GBPJPY.xlsx", "GBPJPYH1-log-12");

		// **********
		// ********** GBPJPYH4-12-h4
		System.out.println("----------------------------------------");
		System.out.println("GBPJPYH1-12-h4");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = Macds.getInputData("data/GBPJPYH4.csv");
		Macds.calculateMacd(macdList, 12, 26, 9);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/GBPJPY.xlsx", "GBPJPYH4-12");
		ExcelFileUtils.writeExcel(printSortList, "output/GBPJPY.xlsx", "GBPJPYH4-sort-12");
		ExcelFileUtils.writeExcel(logList, "output/GBPJPY.xlsx", "GBPJPYH4-log-12");

		// **********
		// ********** GBPJPYH4-12-D1
		System.out.println("----------------------------------------");
		System.out.println("GBPJPYH1-12-D1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = Macds.getInputData("data/GBPJPYD1.csv");
		Macds.calculateMacd(macdList, 12, 26, 9);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/GBPJPY.xlsx", "GBPJPYD1-12");
		ExcelFileUtils.writeExcel(printSortList, "output/GBPJPY.xlsx", "GBPJPYD1-sort-12");
		ExcelFileUtils.writeExcel(logList, "output/GBPJPY.xlsx", "GBPJPYD1-log-12");

		// **********
		// ********** GBPJPYH1-30-h1
		System.out.println("----------------------------------------");
		System.out.println("GBPJPYH1-30-h1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = Macds.getInputData("data/GBPJPYH1.csv");
		Macds.calculateMacd(macdList, 30, 65, 23);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/GBPJPY.xlsx", "GBPJPYH1-30");
		ExcelFileUtils.writeExcel(printSortList, "output/GBPJPY.xlsx", "GBPJPYH1-sort-30");
		ExcelFileUtils.writeExcel(logList, "output/GBPJPY.xlsx", "GBPJPYH1-log-30");

		// **********
		// ********** GBPJPYH4-30-h4
		System.out.println("----------------------------------------");
		System.out.println("GBPJPYH1-30-h4");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = Macds.getInputData("data/GBPJPYH4.csv");
		Macds.calculateMacd(macdList, 30, 65, 23);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/GBPJPY.xlsx", "GBPJPYH4-30");
		ExcelFileUtils.writeExcel(printSortList, "output/GBPJPY.xlsx", "GBPJPYH4-sort-30");
		ExcelFileUtils.writeExcel(logList, "output/GBPJPY.xlsx", "GBPJPYH4-log-30");

		// **********
		// ********** GBPJPYH4-30-D1
		System.out.println("----------------------------------------");
		System.out.println("GBPJPYH1-30-D1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = Macds.getInputData("data/GBPJPYD1.csv");
		Macds.calculateMacd(macdList, 30, 65, 23);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/GBPJPY.xlsx", "GBPJPYD1-30");
		ExcelFileUtils.writeExcel(printSortList, "output/GBPJPY.xlsx", "GBPJPYD1-sort-30");
		ExcelFileUtils.writeExcel(logList, "output/GBPJPY.xlsx", "GBPJPYD1-log-30");
	}
}
