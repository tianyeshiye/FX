package ty.fx.macd;

import java.util.ArrayList;
import java.util.List;

import ty.fx.macd.bean.MacdDataBean;
import ty.fx.macd.bean.MacdResultBean;
import ty.fx.macd.ea.Macd;
import ty.fx.macd.ea.Macds;
import ty.fx.utils.ExcelFileUtils;
import ty.fx.utils.InputFileUtils;

public class JP225 {

	public static void main(String[] args) throws Exception {

		JP225 JP225 = new JP225();

		JP225.test();
	}

	public void test() {

		// DECIMAL_POINT = 2
		Macd instance = new Macd(2);

		List<String> logList;
		List<String> printList;
		List<String> printSortList;
		List<MacdDataBean> macdList;
		List<MacdResultBean> resulList;

		// instance.calculateMacd(macdList, 30, 65, 23);
		// **********
		// ********** JP225H1-12-h1
		System.out.println("----------------------------------------");
		System.out.println("JP225H1-12-h1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = InputFileUtils.getInputData("data/JP225H1.csv");
		Macds.calculateMacd(macdList, 12, 26, 9);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/JP225.xlsx", "H1-12");
		ExcelFileUtils.writeExcel(printSortList, "output/JP225.xlsx", "H1-sort-12");
		ExcelFileUtils.writeExcel(logList, "output/JP225.xlsx", "H1-log-12");

		// **********
		// ********** JP225H4-12-h4
		System.out.println("----------------------------------------");
		System.out.println("JP225H4-12-h4");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = InputFileUtils.getInputData("data/JP225H4.csv");
		Macds.calculateMacd(macdList, 12, 26, 9);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/JP225.xlsx", "H4-12");
		ExcelFileUtils.writeExcel(printSortList, "output/JP225.xlsx", "H4-sort-12");
		ExcelFileUtils.writeExcel(logList, "output/JP225.xlsx", "H4-log-12");

		// **********
		// ********** JP225H4-12-D1
		System.out.println("----------------------------------------");
		System.out.println("JP225D1-12-D1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = InputFileUtils.getInputData("data/JP225D1.csv");
		Macds.calculateMacd(macdList, 12, 26, 9);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/JP225.xlsx", "D1-12");
		ExcelFileUtils.writeExcel(printSortList, "output/JP225.xlsx", "D1-sort-12");
		ExcelFileUtils.writeExcel(logList, "output/JP225.xlsx", "D1-log-12");

		// **********
		// ********** JP225H1-30-h1
		System.out.println("----------------------------------------");
		System.out.println("JP225H1-30-h1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = InputFileUtils.getInputData("data/JP225H1.csv");
		Macds.calculateMacd(macdList, 30, 65, 23);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/JP225.xlsx", "H1-30");
		ExcelFileUtils.writeExcel(printSortList, "output/JP225.xlsx", "H1-sort-30");
		ExcelFileUtils.writeExcel(logList, "output/JP225.xlsx", "H1-log-30");

		// **********
		// ********** JP225H4-30-h4
		System.out.println("----------------------------------------");
		System.out.println("JP225H4-30-h4");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = InputFileUtils.getInputData("data/JP225H4.csv");
		Macds.calculateMacd(macdList, 30, 65, 23);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/JP225.xlsx", "H4-30");
		ExcelFileUtils.writeExcel(printSortList, "output/JP225.xlsx", "H4-sort-30");
		ExcelFileUtils.writeExcel(logList, "output/JP225.xlsx", "H4-log-30");

		// **********
		// ********** JP225H4-30-D1
		System.out.println("----------------------------------------");
		System.out.println("JP225D1-30-D1");
		logList = new ArrayList<String>();
		printList = new ArrayList<String>();
		printSortList = new ArrayList<String>();
		macdList = new ArrayList<MacdDataBean>();
		resulList = new ArrayList<MacdResultBean>();

		macdList = InputFileUtils.getInputData("data/JP225D1.csv");
		Macds.calculateMacd(macdList, 30, 65, 23);

		logList = instance.ea(macdList, resulList);
		printList = Macds.printResult(resulList);
		printSortList = Macds.printSortResult(resulList);

		ExcelFileUtils.writeExcel(printList, "output/JP225.xlsx", "D1-30");
		ExcelFileUtils.writeExcel(printSortList, "output/JP225.xlsx", "D1-sort-30");
		ExcelFileUtils.writeExcel(logList, "output/JP225.xlsx", "D1-log-30");
	}
}
