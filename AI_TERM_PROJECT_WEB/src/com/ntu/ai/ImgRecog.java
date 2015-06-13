package com.ntu.ai;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.util.BatUtils;

/**
 * Servlet implementation class ImgRecog
 */
@WebServlet("/ImgRecog")
public class ImgRecog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(ImgRecog.class);
	   
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImgRecog() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String folderPath = "D:\\#ai_in\\";
		String fileName = request.getParameter("fileName");
		String absolutePath = folderPath + fileName;
		JSONObject jObject = new JSONObject();
		
//		JSONArray jArr = new JSONArray();
//		JSONObject jObj = null;
		
		try {
	        File fcheck = new File(absolutePath);
	        if(fcheck.exists() && fcheck.isFile()) {
				jObject.put("filename", fileName);
				
//				jObj = new JSONObject();
//				jObj.put("id", 1);
//				jObj.put("description", "DANGER");
//				jObj.put("upperleft_x", 50);
//				jObj.put("upperleft_y", 50);
//				jObj.put("lowerright_x", 100);
//				jObj.put("lowerright_y", 100);
//				jArr.put(jObj);	
//				jObj = new JSONObject();
//				jObj.put("id", 2);
//				jObj.put("description", "DANGER");
//				jObj.put("upperleft_x", 100);
//				jObj.put("upperleft_y", 100);
//				jObj.put("lowerright_x", 200);
//				jObj.put("lowerright_y", 200);
//				jArr.put(jObj);	
//				jObject.put("coordinate", jArr);
				jObject.put("coordinate", BatUtils.runTemplate1(absolutePath));
	        }
			
			PrintWriter out = response.getWriter();
			out.println(jObject.toString());
	        log.info(jObject.toString());
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String folderPath = "D:\\#ai_in\\";
		String fileName = "";
		String absolutePath = "";
		JSONObject jObject = new JSONObject();
		
		try {
	        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	        if (isMultipart) {
	            FileItemFactory factory = new DiskFileItemFactory();
	            ServletFileUpload upload = new ServletFileUpload(factory);
	            upload.setSizeMax(1000000000);

	            try {
	                List items = upload.parseRequest(request);
	                Iterator iterator = items.iterator();
	                while (iterator.hasNext()) {
	                    FileItem item = (FileItem) iterator.next();

	                    if (!item.isFormField()) {
	                        fileName = item.getName();
	                        File uploadedFile = new File(folderPath + fileName);
	                        absolutePath = uploadedFile.getAbsolutePath();
	                        log.info(absolutePath);
	                        item.write(uploadedFile);
	                    }
	                }
	            } catch (Exception e) {
	    			e.printStackTrace();
	    		}
	        }
	        
	        jObject.put("success", true);
			
	        PrintWriter out = response.getWriter();
			out.println(jObject.toString());
	        log.info(jObject.toString());
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString(), e);
		}
	}
	
}
