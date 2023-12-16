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
		<div class = "container" style = "float:right">
			<label>Welcome back, <%=user.username%>!</label>
			<% if(user.isAdmin()){ %>
				<br>
				<a href="./index.jsp">User Site</a>
			<% } %>
			<br>
		    <a href = 'logout.jsp'>Logout</a>
		</div>
		<div>
			<h3>User List</h4>
			<input type = "text" id = "txtKeyword" placeholder = "Search username"></input>
			<button onclick = "refreshTaskList();">Refresh</button>
			<br>
			<br>
			<table id = "tblUserList">
				<thead>
					<tr>
						<th>Username</th>
						<th>Max Images Upscaled Daily</th>
						<th>Created</th>
						<th>Updated</th>
						<th>isActive</th>
						<th>isAdmin</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				
				</tbody>
			</table>
			<br>
			<a href = "./addUser.jsp"><button>Add User</button></a>
		</div>
		<script>
			const tblUserList = document.getElementById('tblUserList');
			function sleep(ms) {
	    	    return new Promise(resolve => setTimeout(resolve, ms));
	    	}
	    	
	    	function refreshUserList(){
	    		var xhrGetUserList = new XMLHttpRequest();
	        	xhrGetUserList.onreadystatechange = function() {
	        		if (xhrGetUserList.readyState == XMLHttpRequest.DONE) {
	        			console.log(xhrGetUserList.responseText);
	        			var responseJSON = JSON.parse(xhrGetUserList.responseText);
	        			if(responseJSON.status == "succeeded"){
	        				tblUserList.tBodies[0].innerHTML = "";
	            			
	            			var userList = responseJSON.userList;
	            			for(let i = 0;i<userList.length;i++){
	            				var user = userList[i];
	            				var row = tblUserList.tBodies[0].insertRow();
	            				var cellUsername = row.insertCell(0);
	            				cellUsername.innerHTML = user.username;
	            				var cellMaxImageUpscaled = row.insertCell(1);
	            				cellMaxImageUpscaled.innerHTML = user.maxImageUpscaled;
	            				var cellCreated = row.insertCell(2);
	            				cellCreated.innerHTML = user.created;
	            				var cellUpdated = row.insertCell(3);
	            				cellUpdated.innerHTML = user.updated;
	            				var cellIsActive = row.insertCell(4);
	            				cellIsActive.innerHTML = user.isActive;
	            				var cellIsAdmin = row.insertCell(5);
	            				cellIsAdmin.innerHTML = user.isAdmin;
	            				var cellAction = row.insertCell(6);
	            				cellAction.innerHTML = "<a href = './updateUser.jsp?id="+user.id+"'><button>Update</button></a>"
	            			}
	        			}
	        			
	        		}
	    			
	    		}
	        	xhrGetUserList.open('GET','./GetUserList?keyword='+txtKeyword.value);
	        	xhrGetUserList.send();
	    	}
	
	    	(async () => {
	    		while(true){
	    			refreshUserList()
	            	await sleep(4000);
	    		}
	    		
	    	})();
		</script>
	<% }else{ %>
		<script>
			alert("You are not an admin.");
	    	window.location.replace('./index.jsp');
	    </script>
		
	<% }%>
</body>
</html>