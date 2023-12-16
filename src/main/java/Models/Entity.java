package Models;

import java.math.BigInteger;
import java.time.LocalDateTime;
import Models.DAO;
import Models.Entity.User;
import Constant.Constant;

public class Entity {
	public static class User{
		BigInteger id;
		public String username;
		private String password;
		public BigInteger maxImagesUpscaled;
		public boolean isActive;
		LocalDateTime created;
		LocalDateTime updated;
		private boolean isAdmin;
		public User(BigInteger id, String username, String password, BigInteger maxImagesUpscaled, boolean isActive, LocalDateTime created, LocalDateTime updated,boolean isAdmin) {
			this.id = id;
			this.username = username;
			this.password = password;
			this.maxImagesUpscaled = maxImagesUpscaled;
			this.isActive = isActive;
			this.created = created;
			this.updated = updated;
			this.isAdmin = isAdmin;
		}
		public BigInteger getId() {
			return this.id;
		}
		public LocalDateTime getCreated() {
			return this.created;
		}
		public LocalDateTime getUpdated() {
			return this.updated;
		}
		public boolean isAdmin() {
			return this.isAdmin;
		}
		public int getRemainTasksToday() throws Exception {
			return DAO.getRemainTasksToday(this);
		}
		public String getPassword() {
			return this.password;
		}
	}
	public static class Image{
		BigInteger id; 
		public String name;
		public int width;
		public int height;
		public String extension;
		public BigInteger size;
		BigInteger userId;
		LocalDateTime created;
		public LocalDateTime updated;
		public Image(BigInteger id, String name, int width, int height,String extension, BigInteger size,BigInteger userId, LocalDateTime created, LocalDateTime updated) {
			this.id = id; 
			this.name = name;
			this.width = width;
			this.height = height;
			this.extension = extension;
			this.size = size;
			this.userId = userId;
			this.created = created;
			this.updated = updated;
		}
		public BigInteger getId() {
			return this.id;
		}
		public User getUser() throws Exception {
			return DAO.getUser(this.userId);
		}
	}
	public static class Task{
		BigInteger id;
		public String taskname;
		BigInteger imageId;
		BigInteger userId;
		public int scaleRatio;
		public String status;
		public String result;
		LocalDateTime created;
		public LocalDateTime updated;
		public LocalDateTime startedAt;
		public LocalDateTime endedAt;
		
		public Task(BigInteger id, String taskname, BigInteger imageId, BigInteger userId, int scaleRatio, String status, String result, LocalDateTime created, LocalDateTime updated, LocalDateTime startedAt, LocalDateTime endedAt) {
			this.id = id;
			this.taskname = taskname;
			this.imageId = imageId;
			this.userId = userId;
			this.scaleRatio = scaleRatio;
			this.status = status;
			this.result = result;
			this.created = created;
			this.updated = updated;
			this.startedAt = startedAt;
			this.endedAt = endedAt;
		}
		public BigInteger getId() {
			return this.id;
		} 
		public Image getImage() throws Exception {
			return DAO.getImage(this.imageId);
		}
		public String getUpscaledPath() throws Exception {
			Image image = this.getImage();
			User user = this.getUser();
			String[] splitted = image.name.split("\\.");
			
			String filename = "";
			
			for(int i = 0;i < splitted.length - 1; i++) {
				filename += splitted[i];
				if(i < splitted.length - 2) filename += ".";
			}
			filename += "_out." + splitted[splitted.length - 1];
			
			return Constant.upscaledPath + "/" + user.username + "/" + filename;
		}
		public String getOriginalPath() throws Exception {
			Image image = this.getImage();
			User user = this.getUser();
			
			
			return Constant.uploadPath + "/" + user.username + "/" + image.name;
		}
		public User getUser() throws Exception{
			return DAO.getUser(this.userId);
		}
		public LocalDateTime getCreated() {
			return this.created;
		}
	}
}
