package com.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {
	
	private static Log log = LogFactory.getLog(HttpClientUtils.class);
	
	public static String sendPost(String url, String imagePath) throws ClientProtocolException, IOException {
		
		String result = "";
		
		if (imagePath == null || "".endsWith(imagePath)) {
			log.error("File path not given");
			return result;
		}
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			
			HttpPost httppost = new HttpPost(url);

			File imageFile = new File(imagePath);
			if(imageFile.exists()) {
				
				MultipartEntityBuilder builder = MultipartEntityBuilder.create();
				builder.addTextBody("filename", imageFile.getName(), ContentType.TEXT_PLAIN);
				builder.addBinaryBody("file", imageFile, ContentType.APPLICATION_OCTET_STREAM, imageFile.getName());
				HttpEntity multipart = builder.build();
				httppost.setEntity(multipart);
		
				log.info("executing request " + httppost.getRequestLine());
				CloseableHttpResponse response = httpclient.execute(httppost);
				try {
					log.info("----------------------------------------");
					log.info(response.getStatusLine());
					HttpEntity resEntity = response.getEntity();
					if (resEntity != null) {
						log.info("Response content length: " + resEntity.getContentLength());
						log.info("Response content: " + EntityUtils.toString(resEntity)); // 從這邊讀出作標位置!!
					}
					EntityUtils.consume(resEntity);
				} finally {
					response.close();
				}
			}
		} finally {
			httpclient.close();
		}
		return "out: " + result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			sendPost("http://likisee.ddns.net:8180" + "/AI_TERM_PROJECT_WEB/ImgRecog", "D:\\#ai_out\\777.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
