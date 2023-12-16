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
<title>Image Upscaler</title>
<link rel="stylesheet" href = "static/modal.css">
<link rel="stylesheet" href = "static/table.css">
</head>
<body>
<% if (user != null) {%>
	<div class = "container" style = "float:right">
		<label>Welcome back, <%=user.username%>!</label>
		<% if(user.isAdmin()){ %>
			<br>
			<a href="./admin.jsp">Admin Site</a>
		<% } %>
		<br>
	    <a href = 'logout.jsp'>Logout</a>
	</div>
	<div class = "container" style = "padding-bottom:20px">
	<p>A huge shoutout to xinntao for <a href = "https://github.com/xinntao/Real-ESRGAN">xinntao/Real-ESRGAN</a>
	<br>
	Also, a huge shoutout to TencentARC for Face Enhancement <a href = "https://github.com/TencentARC/GFPGAN">TencentARC/GFPGAN</a> (which xinntao is also a huge contributor)
	<br>
	(This will makes faces look more beautiful, but also make them like being edited)
	<br>
	<br>
	p/s: Because the model <b>regenerates</b> the image using a small amount of parameters, Results <del>might</del> <b>will</b> make you <b>surprised.</b> 
	<br>
	</p>
	</div>
	<div class = "container">
		<input type = "button" id = "btnUploadModal" value = "Upscale Image"></input>
	</div>
	
	<div>
		<h3>Tasks List</h4>
		<input type = "text" id = "txtKeyword" placeholder = "Search Filename"></input>
		<button onclick = "refreshTaskList();">Refresh</button>
		<br>
		<p id = "parRemainTasksToday"></p>
		<table id = "tblTasksList">
			<thead>
				<tr>
					<th>Filename</th>
					<th>Extension</th>
					<th>Height</th>
					<th>Width</th>
					<th>Size</th>
					<th>Taskname</th>
					<th>Scale Ratio</th>
					<th>Status</th>
					<th>Result</th>
					<th>Created At</th>
					<th>Started At</th>
					<th>Ended At</th>
					<th colspan = 2>Action</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
	</div>
	
	
    <div class = "modal" id = "uploadModal">
    	<div class = "modal-content">
    		<span id = "btnCloseModal" class = "close">&times;</span>
    		<label style = "font-size:28; font-weight:bold">Upload</label>
    	</div>
    	<div class="modal-content">
    		<form action = "uploadImage" method = "post" enctype="multipart/form-data">
    			<p>Choose a picture to upload:</p>
    			<p style = "color: red">
    			Requirements:
    			<br>
    			- Your image's total pixel must be lower or equal ${Constant.maxTotalPixels} pixels
    			<br>
    			- Your image's size must be lower or equal ${Constant.maxFileSize} bytes</p>
		    	<input type = "file" id = "fileUpload" name = "image"></input>
		    	<br>
		    	<p>Scale Ratio</p>
		    	<input type = "number" id = "nbRatio" name = "scaleRatio" min = 2 max = 4 value = 4></input>
		    	<br>
		    	<p>Models:</p>
		    	<select name="taskname" id="drpTaskName">
		    		<option value = "upscale_image_ESRNet">ESRNet</option>
					<option value = "upscale_image_ESRGAN" selected="selected">ESRGAN</option>
					<option value = "upscale_image_ESRGAN_anime">ESRGAN_anime</option>
				</select>
				<br>
				<br>
				<label for = "chbFaceEnhancement">Face Enhancement: </label>
				<input type = "checkbox" id = "chbFaceEnhancement"></input>
		    	<br>
		    	<br>
		    	<input type = "button" id = "btnUpload" value = "Upload"></input>
    		</form>
    	</div>
    </div>
    <script>
    	const btnUploadModal = document.getElementById('btnUploadModal');
    	const uploadModal = document.getElementById('uploadModal');
    	const btnCloseModal = document.getElementById('btnCloseModal');
    	
    	const fileUpload = document.getElementById('fileUpload');
    	const nbRatio = document.getElementById('nbRatio');
    	const drpTaskName = document.getElementById('drpTaskName');
    	const chbFaceEnhancement = document.getElementById('chbFaceEnhancement');
    	const btnUpload = document.getElementById('btnUpload');
    	
    	const tblTasksList = document.getElementById('tblTasksList');
    	const parRemainTasksToday = document.getElementById('parRemainTasksToday');
    	const txtKeyword = document.getElementById('txtKeyword');
    	
    	chbFaceEnhancement.checked = true;
    	
    	btnUploadModal.onclick = () => {
    		uploadModal.style.display = "block";
    	};
    	btnCloseModal.onclick = () => {
    		uploadModal.style.display = "none";
    	};
    	
    	var reader = new FileReader;
		reader.onload = function(e)  {
	        var img = document.createElement("img");
	        // the result image data
	        img.src = e.target.result;
	        img.onload = () => {
	        	if(img.width * img.height > ${Constant.maxTotalPixels}){
					
	        		fileUpload.value = '';
	        		alert("Your image's total pixel must be lower or equal ${Constant.maxTotalPixels}\n"
	        				+ "(Your image resolution is " + img.width + " x " + img.height + " = " + img.width * img.height + " pixels)\n\n"
	        				+ "p/s: Forgive my potato");
				}
	        }
	        
	     }
		
    	fileUpload.onchange = () => {
    		if(fileUpload.files[0].size > ${Constant.maxFileSize}){
        		alert("Your image's size must be lower than ${Constant.maxFileSize} bytes\n"
        				+ "(Your image size is " + fileUpload.files[0].size + " bytes)\n\n"
        				+ "p/s: Give up on trying send big file to my server. I configurated on that page so that you cannot send a file bigger than ${Constant.maxFileSize} bytes.");
        		fileUpload.value = '';
    		}
    		reader.readAsDataURL(fileUpload.files[0]);
    	}
    	btnUpload.onclick = () => {
    		var formData = new FormData();
    		var xhr = new XMLHttpRequest();
    		var file = fileUpload.files[0];
    		
    		xhr.onreadystatechange = function() {
    			if (xhr.readyState == XMLHttpRequest.DONE) {
    				var responseJSON = JSON.parse(xhr.responseText);
    				alert(responseJSON.message);
    			}
    		}
    		
    		//formData.append("scaleRatio",nbRatio.value);
    		formData.append("image",file);
    		xhr.open('POST', 'UploadImage?scaleRatio='+nbRatio.value+"&taskname="+drpTaskName.value+"&face_enhance="+chbFaceEnhancement.checked, true);
    		//xhr.setRequestHeader("Content-Type", "multipart/form-data");
    		xhr.send(formData);
    		uploadModal.style.display = "none";
    		
    	}
    	function sleep(ms) {
    	    return new Promise(resolve => setTimeout(resolve, ms));
    	}
    	
    	function refreshTaskList(){
    		var xhrGetTasks = new XMLHttpRequest();
        	xhrGetTasks.onreadystatechange = function() {
        		if (xhrGetTasks.readyState == XMLHttpRequest.DONE) {
        			var responseJSON = JSON.parse(xhrGetTasks.responseText);
        			if(responseJSON.status == "succeeded"){
        				parRemainTasksToday.innerHTML = "<b>Number of photo upscales remains today: " + responseJSON.remainTasksToday + "</b>";
        				tblTasksList.tBodies[0].innerHTML = "";
            			
            			var taskList = responseJSON.taskList;
            			for(let i = 0;i<taskList.length;i++){
            				var task = taskList[i];
            				var row = tblTasksList.tBodies[0].insertRow();
            				var cellFilename = row.insertCell(0);
            				cellFilename.innerHTML = task.image.name;
            				var cellExtension = row.insertCell(1);
            				cellExtension.innerHTML = task.image.extension;
            				var cellHeight = row.insertCell(2);
            				cellHeight.innerHTML = task.image.height;
            				var cellWidth = row.insertCell(3);
            				cellWidth.innerHTML = task.image.width;
            				var cellSize = row.insertCell(4);
            				cellSize.innerHTML = task.image.size;
            				var cellTaskname = row.insertCell(5);
            				cellTaskname.innerHTML = task.taskname;
            				var cellScaleRatio = row.insertCell(6);
            				cellScaleRatio.innerHTML = task.scaleRatio;
            				var cellStatus = row.insertCell(7);
            				cellStatus.innerHTML = task.status;
            				var cellResult = row.insertCell(8);
            				cellResult.innerHTML = (task.result == null || task.result == "null") ? "" : task.result;
            				var cellCreatedAt = row.insertCell(9);
            				cellCreatedAt.innerHTML = (task.created == null || task.created == "null") ? "" : task.created;
            				var cellStartedAt = row.insertCell(10);
            				cellStartedAt.innerHTML = (task.startedAt == null || task.startedAt == "null") ? "" : task.startedAt;
            				var cellEndedAt = row.insertCell(11);
            				cellEndedAt.innerHTML = (task.endedAt == null || task.endedAt == "null") ? "" : task.endedAt;
            				var cellAction1 = row.insertCell(12);
            				cellAction1.innerHTML = task.status == "succeeded" 
            						? "<a href = 'GetUpscaledImage?index=" + i + "&keyword=" + responseJSON.keyword +"'><button>Get Upscaled Image</button>" 
            						: "";
            				var cellAction2 = row.insertCell(13);
            				cellAction2.innerHTML = task.status == "succeeded" 
            						? "<a href = 'GetOriginalImage?index=" + i + "&keyword=" + responseJSON.keyword +"'><button>Get Original Image</button>" 
            						: "";
            			}
        			}
        			
        		}
    			
    		}
        	xhrGetTasks.open('GET','GetTasks?keyword='+txtKeyword.value);
        	xhrGetTasks.send();
    	}

    	(async () => {
    		while(true){
    			refreshTaskList()
            	await sleep(4000);
    		}
    		
    	})();
    	
    	
    </script>
<% } else { %>
    <script>
    	window.location.replace('login.jsp');
    </script>
<% } %>
</body>
</html>