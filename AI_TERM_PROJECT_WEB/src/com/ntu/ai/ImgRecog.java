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
		
		// Get from request
		String test = request.getParameter("test");
		
		// Finally
		PrintWriter out = response.getWriter();
		out.print(test);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String folderPath = "D:\\#ai_in\\";
		String absolutePath = "";
		
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
                        String fileName = item.getName();
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
		
		
		// Finally
		PrintWriter out = response.getWriter();
		out.println("AbsolutePath: " + absolutePath);
		out.close();
	
	}
	
}
