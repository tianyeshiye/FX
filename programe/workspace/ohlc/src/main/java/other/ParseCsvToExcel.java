package other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ty.fx.utils.ExcelFileUtils;

public class ParseCsvToExcel {

	public static void main(String[] args) throws Exception {

		List<List<String>> listList = getInputData("data/signal/EURUSD.csv");

		ExcelFileUtils.writeListExcel(listList, "output/signal/EURUSD.xlsx", "sheetName1");
	}

	public static List<List<String>> getInputData(String filepath) {

		File csv = new File(filepath); // CSV文件路径
		csv.setReadable(true);// 设置可读
		csv.setWritable(true);// 设置可写
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csv));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String line = "";
		List<List<String>> returnList = new ArrayList<List<String>>();

		try {
			while ((line = br.readLine()) != null) // 读取到的内容给line变量
			{

				String[] arrayData = line.split(",");

				String[] array = arrayData[0].split(";");

				List<String> list = Arrays.asList(array);

				returnList.add(list);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnList;
	}

}
