package com.ntu.ai;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ImageUtils;

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
		
//		BufferedImage img = ImageIO.read(new File("d:\\123.png"));
//		String imgstr = ImageUtils.encodeToString(img, "png");
		
//		BufferedImage newImg = ImageUtils.decodeToImage(test);
//		ImageIO.write(newImg, "png", new File("d:\\123_ok_ok.png"));
		
		// Finally
		PrintWriter out = response.getWriter();
		out.print(test);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
