package ty.fx.macd;

import java.util.ArrayList;
import java.util.List;

import ty.fx.macd.bean.MacdDataBean;
import ty.fx.macd.bean.MacdResultBean;
import ty.fx.macd.ea.Macd;
import ty.fx.macd.ea.Macds;
import ty.fx.utils.ExcelFileUtils;
import ty.fx.utils.InputFileUtils;

public class EURUSD {

	public static void main(String[] args) throws Exception {

		EURUSD EURUSD = new EURUSD();

		EURUSD.test();
	}

	public void test() {

		// DECIMAL_POINT = 3
		Macd instance = new Macd(5);

		List<String> logList;
		List<String> printList;
		List<String> printSortList;
		List<MacdDataBean> macdList;
		List<MacdResultBean> resulList;

		// instance.calculateMacd(macdList, 30, 65, 23);
		// **********
		// ********** EURUSDH1-12-h1
		System.out.println("----------------------------------------");
		System.out.println("EURUSDH1-12-h1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = InputFileUtils.getInputData("data/EURUSDH1.csv");
		Macds.calculateMacd(macdList, 12, 26, 9);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/EURUSD.xlsx", "H1-12");
		ExcelFileUtils.writeExcel(printSortList, "output/EURUSD.xlsx", "H1-sort-12");
		ExcelFileUtils.writeExcel(logList, "output/EURUSD.xlsx", "H1-log-12");

		// **********
		// ********** EURUSDH4-12-h4
		System.out.println("----------------------------------------");
		System.out.println("EURUSDH1-12-h4");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = InputFileUtils.getInputData("data/EURUSDH4.csv");
		Macds.calculateMacd(macdList, 12, 26, 9);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/EURUSD.xlsx", "H4-12");
		ExcelFileUtils.writeExcel(printSortList, "output/EURUSD.xlsx", "H4-sort-12");
		ExcelFileUtils.writeExcel(logList, "output/EURUSD.xlsx", "H4-log-12");

		// **********
		// ********** EURUSDH4-12-D1
		System.out.println("----------------------------------------");
		System.out.println("EURUSDH1-12-D1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = InputFileUtils.getInputData("data/EURUSDD1.csv");
		Macds.calculateMacd(macdList, 12, 26, 9);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/EURUSD.xlsx", "D1-12");
		ExcelFileUtils.writeExcel(printSortList, "output/EURUSD.xlsx", "D1-sort-12");
		ExcelFileUtils.writeExcel(logList, "output/EURUSD.xlsx", "D1-log-12");

		// **********
		// ********** EURUSDH1-30-h1
		System.out.println("----------------------------------------");
		System.out.println("EURUSDH1-30-h1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = InputFileUtils.getInputData("data/EURUSDH1.csv");
		Macds.calculateMacd(macdList, 30, 65, 23);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/EURUSD.xlsx", "H1-30");
		ExcelFileUtils.writeExcel(printSortList, "output/EURUSD.xlsx", "H1-sort-30");
		ExcelFileUtils.writeExcel(logList, "output/EURUSD.xlsx", "H1-log-30");

		// **********
		// ********** EURUSDH4-30-h4
		System.out.println("----------------------------------------");
		System.out.println("EURUSDH1-30-h4");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = InputFileUtils.getInputData("data/EURUSDH4.csv");
		Macds.calculateMacd(macdList, 30, 65, 23);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/EURUSD.xlsx", "H4-30");
		ExcelFileUtils.writeExcel(printSortList, "output/EURUSD.xlsx", "H4-sort-30");
		ExcelFileUtils.writeExcel(logList, "output/EURUSD.xlsx", "H4-log-30");

		// **********
		// ********** EURUSDH4-30-D1
		System.out.println("----------------------------------------");
		System.out.println("EURUSDH1-30-D1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = InputFileUtils.getInputData("data/EURUSDD1.csv");
		Macds.calculateMacd(macdList, 30, 65, 23);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/EURUSD.xlsx", "D1-30");
		ExcelFileUtils.writeExcel(printSortList, "output/EURUSD.xlsx", "D1-sort-30");
		ExcelFileUtils.writeExcel(logList, "output/EURUSD.xlsx", "D1-log-30");
	}
}