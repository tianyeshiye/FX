package other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvWriter;

public class CsvFileUtils {

	// 读取csv文件的内容
	public static ArrayList<String> readCsv(String filepath) {
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
		String everyLine = "";
		ArrayList<String> allString = new ArrayList<>();
		try {
			while ((line = br.readLine()) != null) // 读取到的内容给line变量
			{
				everyLine = line;
				System.out.println(everyLine);
				allString.add(everyLine);
			}
			System.out.println("csv表格中所有行数：" + allString.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allString;
	}

	public static void writeCSV(String path, List<String> writearraylist) {
		String csvFilePath = path;

		try {

			// 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
			CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("GBK"));
			// 写内容
			String[] headers = { "FileName", "FileSize", "FileMD5" };
			csvWriter.writeRecord(headers);
			for (int i = 0; i < writearraylist.size(); i++) {
				String[] writeLine = writearraylist.get(i).split(",");
				System.out.println(writeLine);
				csvWriter.writeRecord(writeLine);
			}

			csvWriter.close();
			System.out.println("--------CSV文件已经写入--------");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
