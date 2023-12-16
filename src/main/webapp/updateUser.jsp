<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "Models.BO" %>
<%@ page import = "Models.Entity.*"%>
<%@ page import = "Constant.Constant"%>
<%@ page import = "java.math.BigInteger"%>
<%
BigInteger id = new BigInteger(request.getParameter("id"));
User user = BO.getActiveUser((String)session.getAttribute("username"),(String)session.getAttribute("password"));
User selectedUser = BO.getUser(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href = "static/modal.css">
<link rel="stylesheet" href = "static/table.css">
</head>
<body>
	<% if(user != null && user.isAdmin()){ %>
		<% if(selectedUser!=null){ %>
		<form action = "UpdateUser" method = "POST">
			<h2>Update User</h2>
			<input type = "hidden" name = "id" value = "<%= selectedUser.getId() %>"></input>
			<label>Username: <%=selectedUser.username %></label>
			<br>
			<label>Password</label>
			<br>
			<input type = "password" name = "password" id = "txtPassword" value = "<%=selectedUser.getPassword() %>"></input>
			<br>
			<label>Max Images Upscaled</label>
			<br>
			<input type = "number" name = "maxImagesUpscaled" id = "txtMaxImagesUpscaled" value = "<%=selectedUser.maxImagesUpscaled %>"></input>
			<br>
			<br>
			<label for = "isActive">isActive</label>
			<input type = "checkbox" name = "isActive" id = "chkIsActive" <%=(selectedUser.isActive ? "checked" : "") %>></input>
			<br>
			<label for = "isAdmin">isAdmin</label>
			<input type = "checkbox" name = "isAdmin" id = "chkIsAdmin" <%=(selectedUser.isAdmin() ? "checked" : "") %>></input>
			<br>
			<br>
			<input type = "submit" value = "Update"></input>
		</form>
		<% }else{ %>
		<script>
			alert("User not exist");
	    	window.location.replace('./admin.jsp');
	    </script>
		<% } %>
	<% }else{ %>
		<script>
			alert("You are not an admin.");
	    	window.location.replace('./index.jsp');
	    </script>
		
	<% }%>
</body>
</html>