<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
session.setAttribute("username", null);
session.setAttribute("password", null);
session.setAttribute("admin", null);
response.getWriter().append("<script> \n" +
"alert('Dang xuat thanh cong');\n" +
"window.location.replace('login.jsp');\n"+
"</script>");
%>