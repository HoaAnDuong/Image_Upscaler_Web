package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;

import Models.BO;
import Models.Entity.User;

/**
 * Servlet implementation class GetTasks
 */
public class GetTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTasks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				if(responseJSON != null) {
					String keyword = (String) request.getParameter("keyword");
					if(keyword == null) keyword = "";
					responseJSON = BO.getUserTasksJSON(current_user,keyword);
					responseJSON.put("status", "succeeded");
					responseJSON.put("message", "Tasks List received.");
				}else {
					responseJSON = new JSONObject();
					responseJSON.put("status", "succeeded");
					responseJSON.put("message", "Tasks List failed to receive.");
				}
			}else {
				responseJSON.put("status", "failed");
				responseJSON.put("message", "The session is expired, please reload the page to login again.");
			}
			writer.append(responseJSON.toJSONString());
			
		}catch(Exception e) {
			e.printStackTrace();
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
