package ty.fx.macd.eapoint;

import java.util.ArrayList;
import java.util.List;

import ty.fx.bean.TimeDataBaseBean;
import ty.fx.macd.bean.MacdDataBean;
import ty.fx.macd.bean.MacdResultBean;
import ty.fx.macd.ea.Macds;
import ty.fx.utils.ExcelFileUtils;
import ty.fx.utils.InputFileUtils;

public class MacdPNSwitchPointDriver {

	public static void main(String[] args) throws Exception {

		MacdPNSwitchPointDriver macdPostiveNegativeSwitch = new MacdPNSwitchPointDriver();

		List<Integer> zhiyingPointList = new ArrayList<Integer>();
		List<String> rateList = new ArrayList<String>();

//		//// GBPJPY H4
//		zhiyingPointList = new ArrayList<Integer>();
//		rateList = new ArrayList<String>();
//		for (int i = 1; i < 201; i++) {
//			zhiyingPointList.add(i);
//		}
//		for (int i = 0; i < zhiyingPointList.size(); i++) {
//			int point = zhiyingPointList.get(i);
//			List<String> a = macdPostiveNegativeSwitch.execute("GBPJPY", "H4", 2, point, 12, 26, 9);
//			rateList.addAll(a);
//		}
//		ExcelFileUtils.writeExcel(rateList, "output/PostiveNegativePoint/" + "GBPJPY" + ".xlsx", "H4-12");
//
//		//// GBPJPY D1
//		zhiyingPointList = new ArrayList<Integer>();
//		rateList = new ArrayList<String>();
//		for (int i = 1; i < 501; i++) {
//			zhiyingPointList.add(i);
//		}
//		for (int i = 0; i < zhiyingPointList.size(); i++) {
//			int point = zhiyingPointList.get(i);
//			List<String> a = macdPostiveNegativeSwitch.execute("GBPJPY", "D1", 2, point, 12, 26, 9);
//			rateList.addAll(a);
//		}
//		ExcelFileUtils.writeExcel(rateList, "output/PostiveNegativePoint/" + "GBPJPY" + ".xlsx", "D1-12");
//
//		//// GBPUSD H4
//		zhiyingPointList = new ArrayList<Integer>();
//		rateList = new ArrayList<String>();
//		for (int i = 1; i < 201; i++) {
//			zhiyingPointList.add(i);
//		}
//		for (int i = 0; i < zhiyingPointList.size(); i++) {
//			int point = zhiyingPointList.get(i);
//			List<String> a = macdPostiveNegativeSwitch.execute("GBPUSD", "H4", 4, point, 12, 26, 9);
//			rateList.addAll(a);
//		}
//		ExcelFileUtils.writeExcel(rateList, "output/PostiveNegativePoint/" + "GBPUSD" + ".xlsx", "H4-12");
//
//		//// GBPUSD D1
//		zhiyingPointList = new ArrayList<Integer>();
//		rateList = new ArrayList<String>();
//		for (int i = 1; i < 501; i++) {
//			zhiyingPointList.add(i);
//		}
//		for (int i = 0; i < zhiyingPointList.size(); i++) {
//			int point = zhiyingPointList.get(i);
//			List<String> a = macdPostiveNegativeSwitch.execute("GBPUSD", "D1", 4, point, 12, 26, 9);
//			rateList.addAll(a);
//		}
//		ExcelFileUtils.writeExcel(rateList, "output/PostiveNegativePoint/" + "GBPUSD" + ".xlsx", "D1-12");
//
		//// USDJPY D1
		zhiyingPointList = new ArrayList<Integer>();
		rateList = new ArrayList<String>();
		for (int i = 1; i < 11; i++) {
			zhiyingPointList.add(i);
		}
		for (int i = 0; i < zhiyingPointList.size(); i++) {
			int point = zhiyingPointList.get(i);
			List<String> a = macdPostiveNegativeSwitch.execute("USDJPY", "D1", 2, point, 12, 26, 9);
			rateList.addAll(a);
		}
		ExcelFileUtils.writeExcel(rateList, "output/PostiveNegativePoint/" + "USDJPY" + ".xlsx", "D1-12");
//
//		//// EURJPY D1
//		zhiyingPointList = new ArrayList<Integer>();
//		rateList = new ArrayList<String>();
//		for (int i = 1; i < 501; i++) {
//			zhiyingPointList.add(i);
//		}
//		for (int i = 0; i < zhiyingPointList.size(); i++) {
//			int point = zhiyingPointList.get(i);
//			List<String> a = macdPostiveNegativeSwitch.execute("EURJPY", "D1", 2, point, 12, 26, 9);
//			rateList.addAll(a);
//		}
//		ExcelFileUtils.writeExcel(rateList, "output/PostiveNegativePoint/" + "EURJPY" + ".xlsx", "D1-12");
//
//		//// EURUSD D1
//		zhiyingPointList = new ArrayList<Integer>();
//		rateList = new ArrayList<String>();
//		for (int i = 1; i < 501; i++) {
//			zhiyingPointList.add(i);
//		}
//		for (int i = 0; i < zhiyingPointList.size(); i++) {
//			int point = zhiyingPointList.get(i);
//			List<String> a = macdPostiveNegativeSwitch.execute("EURUSD", "D1", 4, point, 12, 26, 9);
//			rateList.addAll(a);
//		}
//		ExcelFileUtils.writeExcel(rateList, "output/PostiveNegativePoint/" + "EURUSD" + ".xlsx", "D1-12");
//
//		//// JP225 H1
//		zhiyingPointList = new ArrayList<Integer>();
//		rateList = new ArrayList<String>();
//		for (int i = 1; i < 201; i++) {
//			zhiyingPointList.add(i);
//		}
//		for (int i = 0; i < zhiyingPointList.size(); i++) {
//			int point = zhiyingPointList.get(i);
//			List<String> a = macdPostiveNegativeSwitch.execute("JP225", "H1", 0, point, 12, 26, 9);
//			rateList.addAll(a);
//		}
//		ExcelFileUtils.writeExcel(rateList, "output/PostiveNegativePoint/" + "JP225" + ".xlsx", "H1-12");
//
//		//// JP225 H4
//		zhiyingPointList = new ArrayList<Integer>();
//		rateList = new ArrayList<String>();
//		for (int i = 1; i < 201; i++) {
//			zhiyingPointList.add(i);
//		}
//		for (int i = 0; i < zhiyingPointList.size(); i++) {
//			int point = zhiyingPointList.get(i);
//			List<String> a = macdPostiveNegativeSwitch.execute("JP225", "H4", 0, point, 12, 26, 9);
//			rateList.addAll(a);
//		}
//		ExcelFileUtils.writeExcel(rateList, "output/PostiveNegativePoint/" + "JP225" + ".xlsx", "H4-12");
//
//		//// JP225 D1
//		zhiyingPointList = new ArrayList<Integer>();
//		rateList = new ArrayList<String>();
//		for (int i = 1; i < 501; i++) {
//			zhiyingPointList.add(i);
//		}
//		for (int i = 0; i < zhiyingPointList.size(); i++) {
//			int point = zhiyingPointList.get(i);
//			List<String> a = macdPostiveNegativeSwitch.execute("JP225", "D1", 0, point, 12, 26, 9);
//			rateList.addAll(a);
//		}
//		ExcelFileUtils.writeExcel(rateList, "output/PostiveNegativePoint/" + "JP225" + ".xlsx", "D1-12");
	}

	private List<String> execute(String currencyPair, String interval, int decimalPointPara, int zhiyingPoint,
			int macdFast, int macdSlow, int macdColumn) {

		// DECIMAL_POINT = 3
		MacdPNSwitchPoint instance = new MacdPNSwitchPoint(decimalPointPara, zhiyingPoint);

		List<String> logList = new ArrayList<String>();
		// List<String> printList = new ArrayList<String>();
		// List<String> printSortList = new ArrayList<String>();
		List<MacdDataBean> macdList = new ArrayList<MacdDataBean>();
		List<MacdResultBean> resulList = new ArrayList<MacdResultBean>();

		List<String> rateList = new ArrayList<String>();

		// ********** USDJPYH1-12-interval
		System.out.println("----------------------------------------");
		// System.out.println("USDJPYH1-12-h1");
		System.out.println(currencyPair + interval + "-" + macdFast + "-" + interval);

		// macdList = InputFileUtils.getInputData("data/USDJPYH1.csv");
		List<TimeDataBaseBean> timeDataBaseBeanList = InputFileUtils.getInputData("data/5/" + currencyPair + interval + ".csv");
		macdList = Macds.calculateMacd(timeDataBaseBeanList, macdFast, macdSlow, macdColumn);

		logList = instance.ea(macdList, resulList, rateList);

		// printList = Macds.printResult(resulList);
		// printSortList = Macds.printSortResult(resulList);

		// ExcelFileUtils.writeExcel(printList, "output/USDJPY.xlsx", "H1-12");
		// ExcelFileUtils.writeExcel(printList, "output/PostiveNegative/macd-" +
		// currencyPair + ".xlsx",
		// interval + "-" + macdFast);
		// ExcelFileUtils.writeExcel(printSortList, "output/USDJPY.xlsx",
		// "H1-sort-12");
		// ExcelFileUtils.writeExcel(printSortList,
		// "output/PostiveNegative/macd-" + currencyPair + ".xlsx",
		// interval + "-sort-" + macdFast);
		// ExcelFileUtils.writeExcel(logList, "output/USDJPY.xlsx",
		// "H1-log-12");
		 ExcelFileUtils.writeExcel(logList, "output/PostiveNegativePoint/" +
		 currencyPair + ".xlsx",
		 interval + "-" + zhiyingPoint+ "-" + macdFast);

		return rateList;
		// ExcelFileUtils.writeExcelNoDelete(rateList,
		// "output/PostiveNegativePoint/" + currencyPair + ".xlsx",
		// interval + "-Rate-" + macdFast);
	}
}
