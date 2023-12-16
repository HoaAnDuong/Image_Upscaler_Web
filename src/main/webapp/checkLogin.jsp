<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language = "java" import="jakarta.servlet.*" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="Models.BO" %>
<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	PrintWriter writer = response.getWriter();
	try{
		if(BO.getActiveUser(username,password) != null){
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			writer.append("<script> \n" +
			"alert('Dang nhap thanh cong');\n" +
			"</script>");
			response.sendRedirect("index.jsp");
		}else{
			throw new Exception("Ten nguoi dung hoac mat khau bi sai");
		}
	}catch(Exception e){
		e.printStackTrace();
		writer.append("<script> \n" +
		"alert('Dang nhap that bai');" +
		"</script>");
		response.sendRedirect("login.jsp");
	}
%>