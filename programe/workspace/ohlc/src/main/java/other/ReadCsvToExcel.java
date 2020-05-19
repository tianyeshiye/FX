package other;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import ty.fx.utils.ExcelFileUtils;

public class ReadCsvToExcel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		ArrayList<String> list = CsvFileUtils.readCsv("H:/GtihubRepository/FX/研究/李春吉/李春吉03.csv");
//
//		ExcelFileUtils.writeExcelDeleteShangyinhao(list, "H:/GtihubRepository/FX/研究/李春吉/李春吉03.csv.xlsx", "03");
		
		writeMaimaiJiehe();
	}

	private static void writeMaimaiJiehe() {

		List<List<String>> listListAll = read("H:/GtihubRepository/FX/研究/李春吉/李春吉03.csv.xlsx", "03");

		List<List<String>> listListUsdJpyNew = new ArrayList<List<String>>();
		List<List<String>> listListUsdJpyClose = new ArrayList<List<String>>();
		List<List<String>> listListGbpJpyNew = new ArrayList<List<String>>();
		List<List<String>> listListGbpJpyClose = new ArrayList<List<String>>();

		for (int i = 0; i < listListAll.size(); i++) {

			if (listListAll.get(i).get(4).equals("USD/JPY")) {
				if (listListAll.get(i).get(1).equals("FXネオ新規")) {
					listListUsdJpyNew.add(listListAll.get(i));
				} else {
					listListUsdJpyClose.add(listListAll.get(i));
				}
			} else if (listListAll.get(i).get(4).equals("GBP/JPY")) {
				if (listListAll.get(i).get(1).equals("FXネオ新規")) {
					listListGbpJpyNew.add(listListAll.get(i));
				} else {
					listListGbpJpyClose.add(listListAll.get(i));
				}
			}
		}
		
		for (int i = 0; i < listListUsdJpyNew.size(); i++) {

			List<String> newList = listListUsdJpyNew.get(i);

			String 売買区分 = newList.get(5);
			String 約定数量 = newList.get(6);
			String 約定単価 = newList.get(7);

			for (int j = 0; j < listListUsdJpyClose.size(); j++) {

				List<String> closeList = listListUsdJpyClose.get(j);

				String 売買区分1 = closeList.get(5);
				String 約定数量1 = closeList.get(6);
				String 建単価1 = closeList.get(8);

				if (!売買区分.equals(売買区分1) && 約定数量.equals(約定数量1) && 約定単価.equals(建単価1)) {

					newList.add(closeList.get(0)); // 約定日時
					newList.add(closeList.get(1));
					newList.add(closeList.get(2));
					newList.add(closeList.get(3));
					newList.add(closeList.get(4));
					newList.add(closeList.get(5));
					newList.add(closeList.get(6));
					newList.add(closeList.get(7));
					newList.add(closeList.get(8));
					newList.add(closeList.get(9));
					
					listListUsdJpyClose.remove(j);
					break;
				}
			}
		}

		
		
		for (int i = 0; i < listListGbpJpyNew.size(); i++) {

			List<String> newList = listListGbpJpyNew.get(i);

			String 売買区分 = newList.get(5);
			String 約定数量 = newList.get(6);
			String 約定単価 = newList.get(7);

			for (int j = 0; j < listListGbpJpyClose.size(); j++) {

				List<String> closeList = listListGbpJpyClose.get(j);

				String 売買区分1 = closeList.get(5);
				String 約定数量1 = closeList.get(6);
				String 建単価1 = closeList.get(8);

				if (!売買区分.equals(売買区分1) && 約定数量.equals(約定数量1) && 約定単価.equals(建単価1)) {

					newList.add(closeList.get(0)); // 約定日時
					newList.add(closeList.get(1));
					newList.add(closeList.get(2));
					newList.add(closeList.get(3));
					newList.add(closeList.get(4));
					newList.add(closeList.get(5));
					newList.add(closeList.get(6));
					newList.add(closeList.get(7));
					newList.add(closeList.get(8));
					newList.add(closeList.get(9));
					
					listListGbpJpyClose.remove(j);
					break;
				}
			}
		}

		ExcelFileUtils.writeListExcel(listListUsdJpyNew, "H:/GtihubRepository/FX/研究/李春吉/李春吉03.csv.xlsx", "UsdJpyNew");
		ExcelFileUtils.writeListExcel(listListGbpJpyNew, "H:/GtihubRepository/FX/研究/李春吉/李春吉03.csv.xlsx", "GbpJpyNew");
	}

	public static List<List<String>> read(String filePath, String sheetName) {
		List<List<String>> dataLst = null;
		try {

			File finalXlsxFile = new File(filePath);
			Workbook workBook = ExcelFileUtils.getWorkbok(finalXlsxFile);
			dataLst = read(workBook, sheetName);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return dataLst;
	}

	private static List<List<String>> read(Workbook wb, String sheetName) {
		List<List<String>> dataLst = new ArrayList<List<String>>();
		/** 得到第一个shell */
		Sheet sheet = wb.getSheet(sheetName);
		/** 得到Excel的行数 */
		int totalRows = sheet.getPhysicalNumberOfRows();
		int totalCells = 0;
		/** 得到Excel的列数 */
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		/** 循环Excel的行 */
		for (int r = 0; r < totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			List<String> rowLst = new ArrayList<String>();
			/** 循环Excel的列 */
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				String cellValue = "";
				if (null != cell) {
					// 以下是判断数据的类型
					switch (cell.getCellType()) {
					case NUMERIC: // 数字
						cellValue = cell.getNumericCellValue() + "";
						break;
					case STRING: // 字符串
						cellValue = cell.getStringCellValue();
						break;
					case BLANK: // 空值
						cellValue = "";
						break;
					default:
						cellValue = "未知类型";
						break;
					}
				}
				rowLst.add(cellValue);
			}
			/** 保存第r行的第c列 */
			dataLst.add(rowLst);
		}
		return dataLst;
	}

}
