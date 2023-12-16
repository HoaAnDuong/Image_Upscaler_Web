package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import Models.BO;

import Utils.RequestUtil;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			JSONObject requestJSON = RequestUtil.resolveRequestJSON(request);
		    
			Class.forName("org.json.simple.JSONObject");
			
			String username = (String) requestJSON.get("username");
			String password = (String) requestJSON.get("password");
			PrintWriter writer = response.getWriter();
			HttpSession session = request.getSession();
			
			Class.forName("org.json.simple.JSONObject");
			response.setContentType("application/json");
			
			JSONObject responseJSON = new JSONObject();
			
			try{
				if(BO.getActiveUser(username,password) != null){
					session.setAttribute("username", username);
					session.setAttribute("password", password);
					responseJSON.put("status", "succeeded");
					responseJSON.put("message", "Dang nhap thanh cong");
				}else{
					throw new Exception("Ten nguoi dung hoac mat khau bi sai");
				}
			}catch(Exception ex2){
				ex2.printStackTrace();
				responseJSON.put("status", "failed");
				responseJSON.put("message", ex2.getMessage());
			}
			writer.print(responseJSON.toJSONString());
			writer.flush();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
		
		
	}

}
