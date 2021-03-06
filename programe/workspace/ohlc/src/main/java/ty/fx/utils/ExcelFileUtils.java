package ty.fx.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtils {

	public static void main(String[] args) throws Exception {

		List<String> dataList = new ArrayList<String>();

		dataList.add("1,2,3,4,5,6,7");
		dataList.add("1,2,3,6,7");
		dataList.add("1,2,5,6,7");
		dataList.add("1,2,3,4");

		writeExcel(dataList, "output/test.xlsx", "sheetName1");
		writeExcel(dataList, "output/test.xlsx", "sheetName3");
		writeExcel(dataList, "output/test.xlsx", "sheetName2");

	}
	
	public static void writeListExcel(List<List<String>> dataListList, String finalXlsxPath, String sheetName) {
		OutputStream out = null;
		try {
			// 读取Excel文档
			File finalXlsxFile = new File(finalXlsxPath);
			Workbook workBook = getWorkbok(finalXlsxFile);

			int sheetIndex = workBook.getSheetIndex(sheetName);

			if (sheetIndex >= 0) {
				workBook.removeSheetAt(sheetIndex);
			}

			Sheet sheet = workBook.createSheet(sheetName);
			/**
			 * 删除原有数据，除了属性列
			 */
			int rowNumber = sheet.getLastRowNum(); // 第一行从0开始算
			// System.out.println("原始数据总行数，除属性列：" + rowNumber);
			for (int i = 1; i <= rowNumber; i++) {
				Row row = sheet.getRow(i);
				sheet.removeRow(row);
			}
			// 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalXlsxPath);
			workBook.write(out);

			/**
			 * 往Excel中写新数据
			 */
			for (int j = 0; j < dataListList.size(); j++) {

				// 得到要插入的每一条记录
				List<String> dataRow = dataListList.get(j);

				// 创建一行：从第二行开始，跳过属性列
				Row row = sheet.createRow(j);

				for (int k = 0; k < dataRow.size(); k++) {
					// 在一行内循环
					Cell first = row.createCell(k);
					first.setCellValue(dataRow.get(k));
				}
			}
			// 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalXlsxPath);
			workBook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// System.out.println("数据导出成功");
	}

	public static void writeExcel(List<String> dataList, String finalXlsxPath, String sheetName) {
		OutputStream out = null;
		try {
			// 读取Excel文档
			File finalXlsxFile = new File(finalXlsxPath);
			Workbook workBook = getWorkbok(finalXlsxFile);

			int sheetIndex = workBook.getSheetIndex(sheetName);

			if (sheetIndex >= 0) {
				workBook.removeSheetAt(sheetIndex);
			}

			Sheet sheet = workBook.createSheet(sheetName);
			/**
			 * 删除原有数据，除了属性列
			 */
			int rowNumber = sheet.getLastRowNum(); // 第一行从0开始算
			// System.out.println("原始数据总行数，除属性列：" + rowNumber);
			for (int i = 1; i <= rowNumber; i++) {
				Row row = sheet.getRow(i);
				sheet.removeRow(row);
			}
			// 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalXlsxPath);
			workBook.write(out);

			/**
			 * 往Excel中写新数据
			 */
			for (int j = 0; j < dataList.size(); j++) {

				String dataString = dataList.get(j);

				// 得到要插入的每一条记录
				String[] dataArray = dataString.split(",");

				// 创建一行：从第二行开始，跳过属性列
				Row row = sheet.createRow(j);

				for (int k = 0; k < dataArray.length; k++) {
					// 在一行内循环
					Cell first = row.createCell(k);
					first.setCellValue(dataArray[k]);
				}
			}
			// 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalXlsxPath);
			workBook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// System.out.println("数据导出成功");
	}
	
	
	public static void writeExcelNoDelete(List<String> dataList, String finalXlsxPath, String sheetName) {
		OutputStream out = null;
		try {
			// 读取Excel文档
			File finalXlsxFile = new File(finalXlsxPath);
			Workbook workBook = getWorkbok(finalXlsxFile);

			int sheetIndex = workBook.getSheetIndex(sheetName);

			if (sheetIndex >= 0) {
				workBook.removeSheetAt(sheetIndex);
			}

			Sheet sheet = workBook.createSheet(sheetName);
			/**
			 * 删除原有数据，除了属性列
			 */
			int rowNumber = sheet.getLastRowNum(); // 第一行从0开始算
			// System.out.println("原始数据总行数，除属性列：" + rowNumber);

			// 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalXlsxPath);
			workBook.write(out);
			/**
			 * 往Excel中写新数据
			 */
			for (int j = 0; j < dataList.size(); j++) {

				String dataString = dataList.get(j);

				// 得到要插入的每一条记录
				String[] dataArray = dataString.split(",");

				// 创建一行：从第二行开始，跳过属性列
				Row row = sheet.createRow(rowNumber + j);

				for (int k = 0; k < dataArray.length; k++) {
					// 在一行内循环
					Cell first = row.createCell(k);
					first.setCellValue(dataArray[k]);
				}
			}
			// 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalXlsxPath);
			workBook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// System.out.println("数据导出成功");
	}
	
	
	public static void writeExcelDeleteShangyinhao(List<String> dataList, String finalXlsxPath, String sheetName) {
		OutputStream out = null;
		try {
			// 读取Excel文档
			File finalXlsxFile = new File(finalXlsxPath);
			Workbook workBook = getWorkbok(finalXlsxFile);

			int sheetIndex = workBook.getSheetIndex(sheetName);

			if (sheetIndex >= 0) {
				workBook.removeSheetAt(sheetIndex);
			}

			Sheet sheet = workBook.createSheet(sheetName);
			/**
			 * 删除原有数据，除了属性列
			 */
			int rowNumber = sheet.getLastRowNum(); // 第一行从0开始算
			// System.out.println("原始数据总行数，除属性列：" + rowNumber);
			for (int i = 1; i <= rowNumber; i++) {
				Row row = sheet.getRow(i);
				sheet.removeRow(row);
			}
			// 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalXlsxPath);
			workBook.write(out);

			/**
			 * 往Excel中写新数据
			 */
			for (int j = 0; j < dataList.size(); j++) {

				String dataString = dataList.get(j);

				// 得到要插入的每一条记录
				String[] dataArray = dataString.split(",");

				// 创建一行：从第二行开始，跳过属性列
				Row row = sheet.createRow(j);

				for (int k = 0; k < dataArray.length; k++) {
					// 在一行内循环
					Cell first = row.createCell(k);
					first.setCellValue(dataArray[k].replace("\"", ""));
				}
			}
			// 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalXlsxPath);
			workBook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// System.out.println("数据导出成功");
	}

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	public static Workbook getWorkbok(File file) throws IOException {

		// if(!file.exists()){
		// Files.createFile(file.toPath());
		// }

		Workbook wb = null;
		FileInputStream in = new FileInputStream(file);
		if (file.getName().endsWith(EXCEL_XLS)) { // Excel&nbsp;2003
			wb = new HSSFWorkbook(in);
		} else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
			wb = new XSSFWorkbook(in);
		}
		return wb;
	}

}
