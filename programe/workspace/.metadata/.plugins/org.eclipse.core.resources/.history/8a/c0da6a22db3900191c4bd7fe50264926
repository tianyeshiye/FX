package ty.fx.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class FileUtils {

	public static void charOutStream(String fileName, List<String> list) throws IOException {
		// 1：利用File类找到要操作的对象
		File file = new File("output/" + fileName + ".txt");
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		Writer out = new FileWriter(file);

		list.forEach(string -> {

			try {
				out.write(string);
				out.write("\r\n");
			} catch (IOException e) {
				System.out.print(e);
			}

		});

		out.close();
	}

}
