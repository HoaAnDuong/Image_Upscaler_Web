package Models;

import java.math.BigInteger;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Models.Entity.*;
import Constant.Constant;

public class DAO{
	private static String DB_URL = "jdbc:mysql://localhost:3330/images_upscaler";
    private static String USERNAME = "root";
    private static String PASSWORD = "8151114qtc";
    private static int MAXIMAGESUPSCALED = Constant.MAXIMAGESCALED;
    private static final Connection getConnection() throws Exception {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	final Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    	return conn;
    }
    public static User addUser(String username,String password) throws Exception {
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	
    	LocalDateTime ldt_now = LocalDateTime.now();
    	String sql = String.format("INSERT INTO users (username, password, maxImagesUpscaled, isActive, created, updated)"
    			+ " VALUES ('%s','%s',%d,0,'%s','%s');", username,password,MAXIMAGESUPSCALED,ldt_now.toString(),ldt_now.toString());
    	stmt.execute(sql);
    	conn.close();
    	return getUser(username,password);
    }
    public static User updateUser(BigInteger id,String password,BigInteger maxImagesUpscaled,boolean isActive,boolean isAdmin) throws Exception {
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	
    	LocalDateTime ldt_now = LocalDateTime.now();
    	String sql = String.format("UPDATE users SET password = '%s',maxImagesUpscaled = %s, isActive = %d, isAdmin = %d, updated = '%s' WHERE id = %s",
    			password,maxImagesUpscaled.toString(),isActive ? 1 : 0,isAdmin ? 1 : 0,ldt_now.toString(),id.toString());
    	stmt.execute(sql);
    	conn.close();
    	return getUser(id);
    }
    public static User getUser(BigInteger id) throws Exception {
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	String sql = String.format("SELECT * FROM users WHERE id = %s", id.toString());
    	ResultSet rs = stmt.executeQuery(sql);
    	rs.next();
    	if(rs.getString("id") == null) throw new SQLException("Username and/or Password is wrong.");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	User user = new User(new BigInteger(rs.getString("id")), rs.getString("username"), rs.getString("password"),
    			new BigInteger(rs.getString("maxImagesUpscaled")), rs.getInt("isActive") == 1, LocalDateTime.parse(rs.getString("created"),formatter), LocalDateTime.parse(rs.getString("updated"),formatter),rs.getBoolean("isAdmin"));
    	conn.close();
    	return user;
    }
    public static User getUser(String username,String password) throws Exception {
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	String sql = String.format("SELECT * FROM users WHERE username = '%s' AND password = '%s'", username , password);
    	ResultSet rs = stmt.executeQuery(sql);
    	rs.next();
    	if(rs.getString("id") == null) throw new SQLException("Username and/or Password is wrong.");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	User user = new User(new BigInteger(rs.getString("id")), rs.getString("username"), rs.getString("password"),
    			new BigInteger(rs.getString("maxImagesUpscaled")), rs.getInt("isActive") == 1, LocalDateTime.parse(rs.getString("created"),formatter), LocalDateTime.parse(rs.getString("updated"),formatter),rs.getBoolean("isAdmin"));
    	conn.close();
    	return user;
    }
    public static User getUser(String username,String password,String addition) throws Exception {
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	String sql = String.format("SELECT * FROM users WHERE username = '%s' AND password = '%s' %s", username , password,addition);
    	ResultSet rs = stmt.executeQuery(sql);
    	rs.next();
    	if(rs.getString("id") == null) throw new SQLException("Username and/or Password is wrong.");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	User user = new User(new BigInteger(rs.getString("id")), rs.getString("username"), rs.getString("password"),
    			new BigInteger(rs.getString("maxImagesUpscaled")), rs.getInt("isActive") == 1, LocalDateTime.parse(rs.getString("created"),formatter), LocalDateTime.parse(rs.getString("updated"),formatter),rs.getBoolean("isAdmin"));
    	conn.close();
    	return user;
    }
    public static ArrayList<User> getUserList(String keyword) throws Exception {
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	String sql = String.format("SELECT * FROM users WHERE username LIKE '%%%s%%'", keyword);
    	
    	ResultSet rs = stmt.executeQuery(sql);
    	
    	ArrayList<User> userList = new ArrayList<User>();
    	
    	while(rs.next()) {
	    	if(rs.getString("id") == null) throw new SQLException("Username and/or Password is wrong.");
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    	User user = new User(new BigInteger(rs.getString("id")), rs.getString("username"), rs.getString("password"),
    			new BigInteger(rs.getString("maxImagesUpscaled")), rs.getInt("isActive") == 1, LocalDateTime.parse(rs.getString("created"),formatter), LocalDateTime.parse(rs.getString("updated"),formatter),rs.getBoolean("isAdmin"));
	    	userList.add(user);
    	}
    	conn.close();
    	return userList;
    }
    public static Image addImage(String name, int width, int height,String extension, BigInteger size,BigInteger userId) throws Exception{
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	
    	LocalDateTime ldt_now = LocalDateTime.now();
    	String sql = String.format("INSERT INTO images (name,width,height,extension,size,userId,created,updated)\n"+
    						"VALUES ('%s',%d,%d,'%s',%s,%s,'%s','%s')",name,width,height,extension,size.toString(),userId.toString(),ldt_now.toString(),ldt_now.toString());
    	stmt.execute(sql);
    	conn.close();
    	return getImage(name,userId);
    }
    public static Image getImage(BigInteger id) throws Exception{
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	String sql = String.format("SELECT * FROM images WHERE id = %s", id.toString());
    	ResultSet rs = stmt.executeQuery(sql);
    	rs.next();
    	if(rs.getString("id") == null) throw new SQLException("Image not exists.");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	Image image = new Image(new BigInteger(rs.getString("id")),rs.getString("name"),rs.getInt("width"),rs.getInt("height"),rs.getString("extension"),new BigInteger(rs.getString("size")),new BigInteger(rs.getString("userId")), LocalDateTime.parse(rs.getString("created"),formatter), LocalDateTime.parse(rs.getString("updated"),formatter));
    	conn.close();
    	return image;
    }
    public static Image getImage(String name,BigInteger userId) throws Exception{
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	String sql = String.format("SELECT * FROM images WHERE name = '%s' AND userId = %s", name, userId.toString());
    	ResultSet rs = stmt.executeQuery(sql);
    	rs.next();
    	if(rs.getString("id") == null) throw new SQLException("Image not exists.");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	Image image = new Image(new BigInteger(rs.getString("id")),rs.getString("name"),rs.getInt("width"),rs.getInt("height"),rs.getString("extension"),new BigInteger(rs.getString("size")),new BigInteger(rs.getString("userId")), LocalDateTime.parse(rs.getString("created"),formatter), LocalDateTime.parse(rs.getString("updated"),formatter));
    	conn.close();
    	return image;
    }
    public static Task addTask(String taskname, BigInteger imageId, BigInteger userId, int scaleRatio) throws Exception{
    	if(scaleRatio != 2 && scaleRatio != 3 && scaleRatio != 4) throw new Exception("Scale Ratio must be 2, 3 or 4.");
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	LocalDateTime ldt_now = LocalDateTime.now();
    	String sql = String.format("INSERT INTO tasks (taskname,imageId,userId,scaleRatio,status,created,updated)\n"
    			+ "VALUES ('%s',%s,%s,%d,'pending','%s','%s')",taskname,imageId.toString(),userId.toString(),scaleRatio,ldt_now.toString(),ldt_now.toString());
    	stmt.execute(sql);
    	conn.close();
    	return getTask(imageId,userId);
    }
    public static Task getTask(BigInteger id) throws Exception{
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	String sql = String.format("SELECT * FROM tasks WHERE id = %s", id.toString());
    	ResultSet rs = stmt.executeQuery(sql);
    	rs.next();
    	if(rs.getString("id") == null) throw new SQLException("Task not exists.");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	LocalDateTime created = LocalDateTime.parse(rs.getString("created"),formatter);
    	LocalDateTime updated = LocalDateTime.parse(rs.getString("created"),formatter);
    	LocalDateTime startedAt,endedAt;
    	try {
    		startedAt = LocalDateTime.parse(rs.getString("startedAt"),formatter);
    	}catch(Exception e) {
    		startedAt = null;
    	}
    	try {
    		endedAt = LocalDateTime.parse(rs.getString("endedAt"),formatter);
    	}catch(Exception e) {
    		endedAt = null;
    	}
    	
    	Task t = new Task(new BigInteger(rs.getString("id")),rs.getString("taskname"),new BigInteger(rs.getString("imageId")),new BigInteger(rs.getString("userId")),rs.getInt("scaleRatio"),rs.getString("status"),rs.getString("result"),
    			created,updated,startedAt,endedAt);
    	conn.close();
    	return t;
    }
    public static Task getTask(BigInteger imageId, BigInteger userId) throws Exception{
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	String sql = String.format("SELECT * FROM tasks WHERE imageId = %s AND userId = %s", imageId.toString(),userId.toString());
    	ResultSet rs = stmt.executeQuery(sql);
    	rs.next();
    	if(rs.getString("id") == null) throw new SQLException("Task not exists.");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	LocalDateTime created = LocalDateTime.parse(rs.getString("created"),formatter);
    	LocalDateTime updated = LocalDateTime.parse(rs.getString("created"),formatter);
    	LocalDateTime startedAt,endedAt;
    	try {
    		startedAt = LocalDateTime.parse(rs.getString("startedAt"),formatter);
    	}catch(Exception e) {
    		startedAt = null;
    	}
    	try {
    		endedAt = LocalDateTime.parse(rs.getString("endedAt"),formatter);
    	}catch(Exception e) {
    		endedAt = null;
    	}
    	
    	Task t = new Task(new BigInteger(rs.getString("id")),rs.getString("taskname"),new BigInteger(rs.getString("imageId")),new BigInteger(rs.getString("userId")),rs.getInt("scaleRatio"),rs.getString("status"),rs.getString("result"),
    			created,updated,startedAt,endedAt);
    	conn.close();
    	return t;
    }
    public static Task updateTask(Task task) throws Exception {
    	int scaleRatio = task.scaleRatio;
    	if(scaleRatio != 2 && scaleRatio != 3 && scaleRatio != 4) throw new Exception("Scale Ratio must be 2, 3 or 4.");
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	LocalDateTime ldt_now = LocalDateTime.now();

    	String sql = String.format("UPDATE tasks SET taskname = '%s', scaleRatio = %d,status = '%s',result = '%s',updated = '%s',startedAt = %s, endedAt = %s\n"+
    	"WHERE id = %s",task.taskname,task.scaleRatio,task.status,task.result,ldt_now.toString(),
    	task.startedAt != null ? "'" + task.startedAt.toString() + "'": null,
    	task.endedAt != null ? "'" + task.endedAt.toString() + "'": null,task.getId());
    	System.out.println(sql);
    	stmt.execute(sql);
    	conn.close();
    	
    	return getTask(task.getId());
    }
    public static Task getPrioritizedTask() throws Exception {
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	String sql = String.format("SELECT * FROM tasks WHERE status = 'pending' ORDER BY created;");
    	ResultSet rs = stmt.executeQuery(sql);
    	rs.next();
    	if(rs.getString("id") == null) throw new SQLException("Task not exists.");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	LocalDateTime created = LocalDateTime.parse(rs.getString("created"),formatter);
    	LocalDateTime updated = LocalDateTime.parse(rs.getString("created"),formatter);
    	LocalDateTime startedAt,endedAt;
    	try {
    		startedAt = LocalDateTime.parse(rs.getString("startedAt"),formatter);
    	}catch(Exception e) {
    		startedAt = null;
    	}
    	try {
    		endedAt = LocalDateTime.parse(rs.getString("endedAt"),formatter);
    	}catch(Exception e) {
    		endedAt = null;
    	}
    	
    	Task t = new Task(new BigInteger(rs.getString("id")),rs.getString("taskname"),new BigInteger(rs.getString("imageId")),new BigInteger(rs.getString("userId")),rs.getInt("scaleRatio"),rs.getString("status"),rs.getString("result"),
    			created,updated,startedAt,endedAt);
    	conn.close();
    	return t;
    }
    public static ArrayList<Task> getUserTasks(User user,String keyword) throws Exception {
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	String sql = String.format("SELECT * FROM tasks WHERE userId = %s",user.getId().toString());
    	ResultSet rs = stmt.executeQuery(sql);
    	ArrayList<Task> taskList = new ArrayList<Task>();
    	while(rs.next()) {
    		if(rs.getString("id") == null) throw new SQLException("Task not exists.");
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        	LocalDateTime created = LocalDateTime.parse(rs.getString("created"),formatter);
        	LocalDateTime updated = LocalDateTime.parse(rs.getString("created"),formatter);
        	LocalDateTime startedAt,endedAt;
        	try {
        		startedAt = LocalDateTime.parse(rs.getString("startedAt"),formatter);
        	}catch(Exception e) {
        		startedAt = null;
        	}
        	try {
        		endedAt = LocalDateTime.parse(rs.getString("endedAt"),formatter);
        	}catch(Exception e) {
        		endedAt = null;
        	}
        	Task t = new Task(new BigInteger(rs.getString("id")),rs.getString("taskname"),new BigInteger(rs.getString("imageId")),new BigInteger(rs.getString("userId")),rs.getInt("scaleRatio"),rs.getString("status"),rs.getString("result"),
        			created,updated,startedAt,endedAt);
        	
        	if(t.getImage().name.indexOf(keyword) != -1) {
        		taskList.add(t);
    		}
    	}
    	
    	conn.close();
    	return taskList;
    }
    
    public static int getRemainTasksToday(User user) throws Exception {
    	Connection conn = DAO.getConnection();
    	Statement stmt = conn.createStatement();
    	LocalDateTime ldt_now = LocalDateTime.now();
    	LocalDateTime ldt = LocalDateTime.of(ldt_now.getYear(), ldt_now.getMonth(), ldt_now.getDayOfMonth(), 0, 0);
    	String sql = String.format("SELECT COUNT(id) FROM tasks WHERE status != 'failed' AND userId = %s AND created >= '%s'",user.getId(),ldt.toString());
    	ResultSet rs = stmt.executeQuery(sql);
    	rs.next();
    	int upscaledImageCount = rs.getInt(1);
    	int remain = user.maxImagesUpscaled.intValue() - upscaledImageCount;
    	return remain > 0 ? remain : 0;
    }
    
    public static void main(String args[]) {
        try {
        	User user = DAO.getUser("hoaan2003","hoaan2003");
        	System.out.println(getRemainTasksToday(user));
        	//System.out.println(user.created);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
