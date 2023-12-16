package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import org.json.simple.JSONObject;

import Models.BO;
import Models.Entity.User;

/**
 * Servlet implementation class UpdateUser
 */
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
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
			
			User current_user = BO.getActiveUser((String)session.getAttribute("username"),(String)session.getAttribute("password"));
			if(current_user!=null && current_user.isAdmin()) {
				try {
					BigInteger id = new BigInteger(request.getParameter("id"));
					String password = (String) request.getParameter("password");
					BigInteger maxImagesUpscaled = new BigInteger(request.getParameter("maxImagesUpscaled"));
					boolean isActive = request.getParameter("isActive") != null && (request.getParameter("isActive")).compareTo("on") == 0;
					boolean isAdmin = request.getParameter("isAdmin") != null && (request.getParameter("isAdmin")).compareTo("on") == 0;
					BO.updateUser(id, password, maxImagesUpscaled, isActive, isAdmin);
					writer.append("<script>\n"
							+ "alert('User updated successfully.')\n"
							+ "window.location.replace('./admin.jsp')\n"
							+ "</script>");
				}catch(Exception e) {
					e.printStackTrace();
					writer.append("<script>\n"
							+ "alert('User failed to update.')\n"
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
