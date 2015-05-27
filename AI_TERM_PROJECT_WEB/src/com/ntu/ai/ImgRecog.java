package com.ntu.ai;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.sun.media.jfxmedia.logging.Logger;

/**
 * Servlet implementation class ImgRecog
 */
@WebServlet("/ImgRecog")
public class ImgRecog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   
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
		
		request.setCharacterEncoding("UTF-8");
		
		// inputFolder
		String folderPath = "D:\\#ai_in\\";
		
		
//		for(Part part : request.getParts()) {
//			if(! "file".equals(part.getName())) {
//				System.out.println(part);
//			}
//		}
		

//		// filename
//		String partName = "filename";
//		Part part = request.getPart(partName);
//		BufferedReader reader = new BufferedReader( new InputStreamReader(part.getInputStream()));
//		String filename ="";
//		while((filename=reader.readLine())!=null) {
//			System.out.println(filename);
//			break;
//		}
//		
//		// bin
//		Part bin = request.getPart("bin");
//		InputStream is = bin.getInputStream();
//		
//		// write
//		byte[] buffer = new byte[is.available()];
//		is.read(buffer);
//		File targetFile = new File(inputFolder + filename);
//		OutputStream outStream = new FileOutputStream(targetFile);
//		outStream.write(buffer);

		
//		// Finally
//		PrintWriter out = response.getWriter();
//		out.println("AbsolutePath: " + targetFile.getAbsolutePath());
//		out.println("CanonicalPath: " + targetFile.getCanonicalPath());
//		out.close();
	}
	
	private void write(String folderPath, Part part) throws IOException, FileNotFoundException {
		String header = part.getHeader("Content-Disposition");
		String filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
		write(folderPath, filename, part.getInputStream());
	}

	private void write(String folderPath, String filename, InputStream in)	throws IOException, FileNotFoundException {
		OutputStream out = new FileOutputStream("folderPath" + filename);
		byte[] buffer = new byte[1024];
		int length = -1;
		while ((length = in.read(buffer)) != -1) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();
	}
	
}
