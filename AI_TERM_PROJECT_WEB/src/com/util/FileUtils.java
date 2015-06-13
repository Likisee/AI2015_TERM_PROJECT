package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {
	
	public static String readFileAsString(File file) {
		String targetFileStr = "";
		try {
			FileInputStream fisTargetFile = new FileInputStream(file);
			targetFileStr = org.apache.commons.io.IOUtils.toString(fisTargetFile, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return targetFileStr;
	}
	
	public static String readFileAsString(String filePath) {
		return readFileAsString(new File(filePath));
	}
	
	public static String readFileAsString(String fileFolder, String filename) {
		return readFileAsString(new File(fileFolder + File.separator + filename));
	}
	
	public static String writeStringToFile(File file, String content) {
		String absoluteFile = "";
		try {
			org.apache.commons.io.FileUtils.writeStringToFile(file, content, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return absoluteFile;
	}
	
	public static String writeStringToFile(String filePath, String content) {
		return writeStringToFile(new File(filePath), content);
	}

	public static String writeStringToFile(String fileFolder, String filename, String content) {
		return writeStringToFile(new File(fileFolder + File.separator + filename), content);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
