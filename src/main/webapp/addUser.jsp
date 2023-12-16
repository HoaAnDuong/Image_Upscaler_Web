<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "Models.BO" %>
<%@ page import = "Models.Entity.*"%>
<%@ page import = "Constant.Constant"%>
<%
User user = BO.getActiveUser((String)session.getAttribute("username"),(String)session.getAttribute("password"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Site</title>
<link rel="stylesheet" href = "static/modal.css">
<link rel="stylesheet" href = "static/table.css">
</head>
<body>
	<% if(user != null && user.isAdmin()){ %>
		<form action = "AddUser" method = "POST">
			<h2>Add User</h2>
			<label>Username</label>
			<br>
			<input type = "text" name = "username" id = "txtUsername"></input>
			<br>
			<label>Password</label>
			<br>
			<input type = "password" name = "password" id = "txtPassword"></input>
			<br>
			<br>
			<input type = "submit" value = "Add"></input>
		</form>
	<% }else{ %>
		<script>
			alert("You are not an admin.");
	    	window.location.replace('./index.jsp');
	    </script>
		
	<% }%>
</body>
</html>