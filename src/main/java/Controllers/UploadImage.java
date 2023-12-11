package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;

import Constant.Constant;
import Models.BO;
import Models.Entity.*;

import Utils.RequestUtil;

import Constant.Constant;

/**
 * Servlet implementation class uploadImage
 */
@WebServlet(name = "UploadImage", urlPatterns = {"/UploadImage"})
@MultipartConfig(maxFileSize = Constant.maxFileSize)
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public UploadImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			Class.forName("org.json.simple.JSONObject");
			PrintWriter writer = response.getWriter();
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			JSONObject responseJSON = new JSONObject();
			User current_user= BO.getActiveUser(username, password);
			if(current_user!=null) {
				int scaleRatio = Integer.parseInt(request.getParameter("scaleRatio"));
				String taskname = request.getParameter("taskname");
				boolean face_enhance = request.getParameter("face_enhance").compareTo("true") == 0;
				Part image = request.getPart("image");
				responseJSON = BO.uploadImage(current_user, image, scaleRatio,taskname,face_enhance);
				
				
			}else {
				responseJSON.put("status", "failed");
				responseJSON.put("message", "The session is expired, please reload the page to login again.");
			}
			writer.append(responseJSON.toJSONString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
