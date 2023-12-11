package Models;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Constant.Constant;
import Models.*;
import Models.Entity.*;
import jakarta.servlet.http.Part;
public class BO {
	public static User getUser(String username,String password) throws Exception {
		try {
			return DAO.getUser(username, password);
		}catch(Exception ex) {
			//ex.printStackTrace();
			return null;
		}
		
	}
	public static User getActiveUser(String username,String password) throws Exception {
		try {
			return DAO.getUser(username, password,"AND isActive = 1");
		}catch(Exception ex) {
			//ex.printStackTrace();
			return null;
		}
		
	}
	public static Image getImage(String name,User user) {
		try {
			return DAO.getImage(name,user.getId());
		}catch(Exception ex) {
			//ex.printStackTrace();
			return null;
		}
	} 
	public static Task getPrioritizedTask() {
		try {
			return DAO.getPrioritizedTask();
		}catch(Exception ex) {
			//ex.printStackTrace();
			return null;
		}
	}
	public static Task updateTask(Task task) {
		try {
			return DAO.updateTask(task);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public static ArrayList<Task> getUserTasks(User user,String keyword) {
		try {
			return DAO.getUserTasks(user,keyword);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public static JSONObject uploadImage(User user,Part image,int scaleRatio,String taskname,boolean face_enhance) {
		try
		{
			Class.forName("org.json.simple.JSONObject");
			JSONObject responseJSON = new JSONObject();
			try {
				
				if(user.getRemainTasksToday() <= 0) throw new Exception("You've run out of times to upscale your images today.");
				if(image.getSubmittedFileName() == null) throw new Exception("Image field cannot be blank.");
				if(scaleRatio != 2 && scaleRatio != 3 && scaleRatio != 4) throw new Exception("Scale Ratio must be 2, 3 or 4.");
				BufferedImage buffImage = ImageIO.read(image.getInputStream());
				
				long size = image.getSize();
				int height = buffImage.getHeight();
				int width = buffImage.getWidth();

				
				if(height * width > Constant.maxTotalPixels) throw new Exception(String.format("Bro, my potato cannot upscale a image having more than %d pixels due to the deadlock.\n(Your image have %d pixels)",
						Constant.maxTotalPixels,height * width));
				
				int i = 0;
				String filename = image.getSubmittedFileName();
				
				String[] strArray = filename.split("\\.");
				String extension = strArray[strArray.length-1];
				
				if(!Constant.cv2SupportedList.contains(extension)) throw new Exception(String.format("Your image's extension is not supported.\n"
						+ "The supported extension are: " + Constant.cv2SupportedList));
				
				String new_filename = filename;
				while(BO.getImage(new_filename, user)!=null) {
					i+=1;
					new_filename = "(" + i + ") " + filename;
				}		
				System.out.println(new_filename);
				new File(Constant.uploadPath+"\\"+user.username).mkdirs();
				image.write(Constant.uploadPath+"\\"+user.username+"\\"+new_filename);
				
				if(taskname == null) taskname = "upscale_image_ESRGAN";
				
				taskname += face_enhance ? "_face_enhance" : "";
				
				Image imageEntity = DAO.addImage(new_filename, width, height, extension, BigInteger.valueOf(size),user.getId());
				DAO.addTask(taskname, imageEntity.getId(), user.getId(), scaleRatio);
				responseJSON.put("status", "suceeded");
				responseJSON.put("message", "Image uploaded successfully. Now, You need to wait our program to rescale you image.");
			}catch(Exception e) {
				e.printStackTrace();
				responseJSON.put("status", "failed");
				responseJSON.put("message", e.getMessage());
			}
			
			return responseJSON;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public static JSONObject getUserTasksJSON(User user,String keyword) throws Exception {
		try {
			Class.forName("org.json.simple.JSONObject");
			Class.forName("org.json.simple.JSONArray");
			JSONObject responseJSON = new JSONObject();
			responseJSON.put("username", user.username);
			responseJSON.put("remainTasksToday", user.getRemainTasksToday());
			responseJSON.put("keyword", keyword);
			
			ArrayList<Task> taskList = DAO.getUserTasks(user,keyword);
			if(taskList != null) {
				JSONArray tasksListJSON = new JSONArray();
				for(Task t: taskList) {
					JSONObject taskJSON = new JSONObject();
					
					taskJSON.put("taskname", t.taskname);
					taskJSON.put("scaleRatio", t.scaleRatio);
					taskJSON.put("status", t.status);
					taskJSON.put("result", t.result);
					taskJSON.put("startedAt", t.startedAt != null ? t.startedAt.toString() : null);
					taskJSON.put("endedAt", t.endedAt != null ? t.endedAt.toString() : null);
					taskJSON.put("created", t.getCreated().toString());
					taskJSON.put("updated", t.updated.toString());
					
					
					Image img = t.getImage();
					JSONObject imageJSON = new JSONObject();
					imageJSON.put("name", img.name);
					imageJSON.put("extension", img.extension);
					imageJSON.put("height", img.height);
					imageJSON.put("width", img.width);
					imageJSON.put("size", img.size);
					
					taskJSON.put("image", imageJSON);
					tasksListJSON.add(taskJSON);
				}
				responseJSON.put("taskList", tasksListJSON);
			}else {
				throw new Exception("taskList is null.");
			}
			return responseJSON;
		}catch(Exception e) {
			//e.printStackTrace();
			return null;
		}
		
	}
}
