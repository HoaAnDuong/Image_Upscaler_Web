package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import Models.BO;
import Models.Entity.Task;
import Models.Entity.User;

/**
 * Servlet implementation class GetUpscaledImage
 */
public class GetUpscaledImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUpscaledImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Class.forName("org.json.simple.JSONObject");
			
			
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			JSONObject responseJSON = new JSONObject();
			User current_user= BO.getActiveUser(username, password);
			String keyword = request.getParameter("keyword");
			if(keyword == null) keyword = "";
			if(current_user!=null) {
				int index = Integer.parseInt(request.getParameter("index"));
				
				ArrayList<Task> tasksList = BO.getUserTasks(current_user,keyword);
				
				Task t = tasksList.get(index);
				
				// TODO Auto-generated method stub
				response.setContentType("image/jpeg");
		          
		        // path of the image 
		        FileInputStream fin = new FileInputStream(t.getUpscaledPath());   
		        
		        ServletOutputStream out; 
		          
		        // Writing this image  
		        // content as a response  
		        out = response.getOutputStream(); 
		  
		        // getting image in BufferedInputStream   
		        BufferedInputStream bin = new BufferedInputStream(fin); 
		        BufferedOutputStream bout = new BufferedOutputStream(out);   
		          
		        int ch =0;   
		        while((ch=bin.read())!=-1)   
		        {   
		            // display image 
		            bout.write(ch);   
		        }   
		          
		        // close all classes 
		        bin.close();   
		        fin.close();   
		        bout.close();   
		        out.close();
				
			}else {
				PrintWriter writer = response.getWriter();
				response.setStatus(404);
				writer.write("The session is expired, please login again.");
			}
			
		}catch(Exception e) {
			//e.printStackTrace();
			PrintWriter writer = response.getWriter();
			response.setStatus(404);
			writer.write("File not founds");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
