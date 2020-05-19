package other;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import ty.fx.utils.ExcelFileUtils;

public class ReadExcel {

	public static void main(String[] args) {

		readExcel("H:\\GtihubRepository\\FX\\研究\\交易记录.xlsx");
	}

	private static void readExcel(String path) {

		List<List<String>> newListList = new ArrayList<List<String>>();

		List<List<String>> listListAll = read(path, "Sheet1");

		for (List<String> rowList : listListAll) {

			if (rowList.size() == 14 && !rowList.contains("")) {

				rowList.set(13, rowList.get(13).replace(" ", ""));
				newListList.add(rowList);
			}
		}
		ExcelFileUtils.writeListExcel(newListList, "H:\\GtihubRepository\\FX\\研究\\交易记录.xlsx", "new");

		Map<String, List<List<String>>> typeHash = new HashMap<String, List<List<String>>>();

		List<String> listList0 = newListList.get(0);
		newListList.remove(0);
		
		List<String> filterList = new ArrayList<String>();
		
		filterList.add("us_oil");
		filterList.add("oilusd.");
		filterList.add("usdchf.");
		filterList.add("xagusd.");
		
		filterList.add("xagusd.");
		filterList.add("euraud.");
		filterList.add("cadchf.");
		filterList.add("audcad.");
		filterList.add("usdcad.");
		filterList.add("cadjpy.");
		filterList.add("");
		filterList.add("");
		filterList.add("");
		
		for (List<String> rowList : newListList) {
			String type = rowList.get(4);

			if(filterList.contains(type)) {
				continue;
			}

			List<List<String>> list;

			if (typeHash.containsKey(type)) {

				list = typeHash.get(type);
			} else {
				list = new ArrayList<List<String>>();
				typeHash.put(type, list);
			}
			list.add(rowList);
		}
		
		for (Map.Entry<String,List<List<String>>> entry : typeHash.entrySet()){
			String type = entry.getKey();
			List<List<String>> listList = entry.getValue();
			
			listList.add(0, listList0);
            
            ExcelFileUtils.writeListExcel(listList, "H:\\GtihubRepository\\FX\\研究\\交易记录.xlsx", type);
		}
		
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
