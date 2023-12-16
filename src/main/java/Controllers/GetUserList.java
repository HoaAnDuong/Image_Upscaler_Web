package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import Models.BO;
import Models.Entity.User;

/**
 * Servlet implementation class getUserList
 */
@WebServlet(name = "GetUserList", urlPatterns = {"/GetUserList"})
public class GetUserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserList() {
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
			if(current_user!=null && current_user.isAdmin()) {
				if(responseJSON != null) {
					String keyword = (String) request.getParameter("keyword");
					if(keyword == null) keyword = "";
					
					JSONArray userList = new JSONArray();
					
					for(User user:BO.getUserList(keyword)) {
						JSONObject userJSON = new JSONObject();
						userJSON.put("id", String.format("%s", user.getId().toString()));
						userJSON.put("username",String.format("%s", user.username.toString()) );
						userJSON.put("maxImageUpscaled", String.format("%s", user.maxImagesUpscaled.toString()) );
						userJSON.put("created", String.format("%s", user.getCreated().toString()) );
						userJSON.put("updated", String.format("%s", user.getUpdated().toString()) );
						userJSON.put("isActive", String.format("%s", (user.isActive ? "true" : "false")) );
						userJSON.put("isAdmin", String.format("%s", (user.isAdmin() ? "true" : "false")) );
						userList.add(userJSON);
					}
					responseJSON.put("userList", userList);
					responseJSON.put("status", "succeeded");
					responseJSON.put("message", "User List received.");
				}else {
					responseJSON = new JSONObject();
					responseJSON.put("status", "failed");
					responseJSON.put("message", "User List failed to receive.");
				}
			}else {
				responseJSON.put("status", "failed");
				responseJSON.put("message", "You need to be an admin to see the User List");
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
