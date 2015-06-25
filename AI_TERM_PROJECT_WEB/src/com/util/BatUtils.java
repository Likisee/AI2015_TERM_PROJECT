package com.util;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BatUtils {
	private static Log log = LogFactory.getLog(BatUtils.class);

	public static JSONArray runTemplate1(String filePath) {

		if(filePath.contains(".")) {
			filePath = filePath.substring(0, filePath.lastIndexOf("."));
		}
		
		String filePathJpg = filePath + ".jpg";
		String filePathTxt = filePath + ".txt";
		String filePathBat = filePath + ".bat";
		
		// composite bat
		String batContent = "d:" + "\r\n";
		batContent += "cd \\" + "\r\n";
		batContent += "cd D:\\#ai_bin\\beta2" + "\r\n";
		batContent += "Face3 " + filePathJpg + " > " + filePathTxt + "\r\n";
		batContent += "exit(1)" + "\r\n";
		FileUtils.writeStringToFile(filePathBat, batContent);
		
		// execute
		run(filePathBat);
		
		// read
		JSONArray jArr = new JSONArray();
		String [] contentArr = FileUtils.readFileAsString(filePathTxt).split("\r\n");
		for(int i = 1; i < contentArr.length; i++) {
			try {
				String [] onelineArr = contentArr[i].split(" ");
				if(onelineArr.length > 1) {
					JSONObject jObj = new JSONObject();
					jObj.put("id", i);
					jObj.put("description", "DANGER");
					jObj.put("upperleft_x", onelineArr[0]);
					jObj.put("upperleft_y", onelineArr[1]);
					jObj.put("lowerright_x", onelineArr[2]);
					jObj.put("lowerright_y", onelineArr[3]);
					jArr.put(jObj);					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
//    	log.info(jArr.toString());
		return jArr;
	}
	
	public static JSONArray runTemplate1OnlyTxt(String filePath) {

		if(!filePath.endsWith(".txt")) {
			return null;
		}
		
		// read
		JSONArray jArr = new JSONArray();
		String [] contentArr = FileUtils.readFileAsString(filePath).split("\r\n");
		for(int i = 1; i < contentArr.length; i++) {
			try {
				String [] onelineArr = contentArr[i].split(" ");
				if(onelineArr.length > 1) {
					JSONObject jObj = new JSONObject();
					jObj.put("id", i);
					jObj.put("description", "DANGER");
					jObj.put("upperleft_x", onelineArr[0]);
					jObj.put("upperleft_y", onelineArr[1]);
					jObj.put("lowerright_x", onelineArr[2]);
					jObj.put("lowerright_y", onelineArr[3]);
					jArr.put(jObj);					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
//    	log.info(jArr.toString());
		return jArr;
	}
	
	public static void run(String batPath){
		Runtime runtime = Runtime.getRuntime();
		try {
		    Process p1 = runtime.exec("cmd /c start " + batPath);
		    InputStream is = p1.getInputStream();
		    int i = 0;
		    while( (i = is.read() ) != -1) {
		    	log.info((char)i);
		    }
		} catch(IOException ioException) {
			log.error(ioException.toString(), ioException);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runTemplate1("D:\\#ai_bin\\beta2\\777.jpg");
	}
}
