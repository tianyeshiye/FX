package ty.fx.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ty.fx.bean.TimeDataBaseBean;
import ty.fx.macd.bean.MacdDataBean;

public class InputFileUtils {

	public static List<MacdDataBean> getInputData(String filepath) {

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
		List<MacdDataBean> list = new ArrayList<MacdDataBean>();

		try {
			while ((line = br.readLine()) != null) // 读取到的内容给line变量
			{

				String[] arrayData = line.split(",");

				MacdDataBean bean = new MacdDataBean();
				bean.setTimeYMDHM(arrayData[0].replace(".", ""));
				bean.setTimeH(arrayData[1].substring(0, 2));
				bean.setOpen(Float.valueOf(arrayData[2]));
				bean.setHigh(Float.valueOf(arrayData[3]));
				bean.setLow(Float.valueOf(arrayData[4]));
				bean.setClose(Float.valueOf(arrayData[5]));

				list.add(bean);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
}
