package main.java.cn.fan.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 提取java 文件
 * 
 * @author fan
 *
 */
public class ExtractJava {

	public static void accessDirectory(File directory, List<String> javaFilePaths) throws IOException {
		if (directory.isDirectory()) {
			File[] listFiles = directory.listFiles();
			for (File file : listFiles) {
				if (file.isDirectory()) {
					accessDirectory(file, javaFilePaths);
				} else if (file.isFile() && file.getAbsolutePath().endsWith(".java")) {
					javaFilePaths.add(file.getAbsolutePath());
				}
			}
		}
	}

	/**
	 * 输入path 返回路径的两部分1.文件名字 2.文件的路径
	 * 
	 * @param path 需要拆分的string
	 * @return
	 */
	public static String[] pathSplit(String path) {
		String[] results = new String[2];
		int localtion = path.lastIndexOf("\\");
		results[0] = path.substring(0, localtion + 1);
		results[1] = path.substring(localtion + 1);
		return results;
	}

	public static void main(String[] args) {
		List<String> javaFilePaths = new ArrayList<String>();
		try {
			accessDirectory(new File("C:\\Users\\Thinkpad\\git\\poi\\src"), javaFilePaths);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String s : javaFilePaths) {
			String[] pathSplit = pathSplit(s);
			System.out.println(pathSplit[0] + "\t" + pathSplit[1]);
		}
	}
}
