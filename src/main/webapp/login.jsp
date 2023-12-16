<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<form action = "Login" method = "post">
	<label>Username</label>
	<br>
	<input type = "text" name = "username" id = "txtUsername"></input>
	<br>
	<label>Password</label>
	<br>
	<input type = "password" name = "password" id = "txtPassword"></input>
	<br>
	<p id = "errLogin" style = "color: red"></p>
	<input type = "button" id = "btnSubmit" value = "Submit">
	</form>
</body>
<script>
	var btnSubmit = document.getElementById('btnSubmit');
	btnSubmit.onclick = (async () =>{
		document.getElementById('errLogin').innerHTML = "";
		console.log("Pressed");
		var response = await fetch("Login", {
		  method: "post",
		  headers: {
		    'Accept': 'application/json',
		    'Content-Type': 'application/json'
		  },
		  body: JSON.stringify({
		    username: document.getElementById('txtUsername').value,
		    password: document.getElementById('txtPassword').value
		  })
		})
		.then((response) => response.json());
		
		if(response.status == "failed"){
			document.getElementById('errLogin').innerHTML = response.message;
		}
		if(response.status == "succeeded"){
			alert(response.message);
			window.location.replace("index.jsp");
		}
	});
</script>
</html>