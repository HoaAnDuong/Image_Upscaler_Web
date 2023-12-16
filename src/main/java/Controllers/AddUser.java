package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Models.BO;
import Models.Entity.User;

/**
 * Servlet implementation class AddUser
 */
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
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
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			JSONObject responseJSON = new JSONObject();
			User current_user = BO.getActiveUser((String)session.getAttribute("username"),(String)session.getAttribute("password"));
			if(current_user!=null && current_user.isAdmin()) {
				try {
					BO.addUser(username, password);
					writer.append("<script>\n"
							+ "alert('User is added successfully.')\n"
							+ "window.location.replace('./admin.jsp')\n"
							+ "</script>");
				}catch(Exception e) {
					writer.append("<script>\n"
							+ "alert('User failed to be added.')\n"
							+ "window.location.replace('./admin.jsp')\n"
							+ "</script>");
				}
			}else {
				writer.append("<script>\n"
						+ "alert('You are not an admin.')\n"
						+ "window.location.replace('./index.jsp')\n"
						+ "</script>");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
