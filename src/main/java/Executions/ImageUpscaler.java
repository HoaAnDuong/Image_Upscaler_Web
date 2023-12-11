package Executions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import Models.BO;
import Models.Entity.*;
import Constant.Constant;

public class ImageUpscaler {
	public static int numWorker = 3;//if you have a stronger potato, increase it
	public static HashMap<String,String> tasknameMap = Constant.getTasknameMap();
	public static void main(String args[]) throws InterruptedException {
		ArrayList<Worker> workerList = new ArrayList<Worker>();
		for(int i = 0; i < numWorker;i++) {
			System.out.println(i);
			Worker new_worker = new Worker();
			new_worker.previousWorker = i-1 >=0 ? workerList.get(i-1) : null;
			workerList.add(new_worker);
			
			new Thread(new_worker).start();
			Thread.sleep(1000);
		}
	}
	
	private static class Worker implements Runnable{
		public boolean isWorking = false;
		public Worker previousWorker = null;
		public boolean previousHaveTask() {
			return this.previousWorker == null || this.previousWorker.isWorking;
		}
		@Override
		public void run() {
			try {
				while(true) {
					Thread.sleep(5000);
					Task selected_task = this.previousHaveTask() ? BO.getPrioritizedTask() : null; 
					//if previous worker doesn't have job, it's will wait until the previous have the job
					//I know that's algorithm is bad but i don't want to see 2 workers doing same job. That's hurt
					if(selected_task!=null) {
						this.isWorking = true;
						try {
							LocalDateTime startedAt = LocalDateTime.now();
							selected_task.status = "processing";
							selected_task.startedAt = startedAt;
							selected_task.result = "Image is being processed.";
							User user = selected_task.getUser();
							Image image = selected_task.getImage();
							selected_task = BO.updateTask(selected_task);
							
							
							if(!Constant.cv2SupportedList.contains(image.extension)) throw new Exception("Extension unsupported.");
							
							String commandPattern = tasknameMap.get(selected_task.taskname);
							
							if(commandPattern == null) throw new Exception("Invalid taskname.");
							
							String command = String.format(commandPattern,
									user.username,image.name,user.username,selected_task.scaleRatio);
							System.out.println("Command:" + command);
							
							System.out.println(String.format("Command %s started at: %s",command,startedAt));
							
							
				            Process p = Runtime.getRuntime().exec(command);
							
				            BufferedReader stdInput = new BufferedReader(new 
				                 InputStreamReader(p.getInputStream()));

				            BufferedReader stdError = new BufferedReader(new 
				                 InputStreamReader(p.getErrorStream()));

				            String s;
				            
				            // read the output from the command
				            //System.out.println("Here is the standard output of the command:\n");
				            while ((s = stdInput.readLine()) != null) {
				                System.out.println(s);
				            }
				            
				            // read any errors from the attempted command
				            //System.out.println("Here is the standard error of the command (if any):\n");
				            while ((s = stdError.readLine()) != null) {
				                System.out.println(s);
				            }
				            int returnValue = p.waitFor();
				            
				            this.isWorking = false;
				            
				            if(this.previousWorker != null) {
				            	System.out.println("Thread " + this.toString() + " is being idle.");
				            	Thread.sleep(3000);
				            }
				            
				            LocalDateTime endedAt = LocalDateTime.now();
				            System.out.println(String.format("Command %s ended at: %s",command,endedAt));
				            
				            if(returnValue == 0) {
				            	selected_task.status = "succeeded";
								selected_task.endedAt = endedAt;
								selected_task.result = "Image upscaled successfully.";
								selected_task = BO.updateTask(selected_task);
				            }else {
				            	System.out.println("Error Occured in Image Upscaler.\"");
				            	selected_task.status = "failed";
								selected_task.endedAt = endedAt;
								selected_task.result = "Error Occured in Image Upscaler.";
								selected_task = BO.updateTask(selected_task);
				            }
				            
						}catch(Exception e) {
							System.out.println(e.getMessage());
							LocalDateTime endedAt = LocalDateTime.now();
				            System.out.println(String.format("Command ended at: %s",endedAt));
							selected_task.status = "failed";
							selected_task.endedAt = endedAt;
							selected_task.result = e.getMessage();
							selected_task = BO.updateTask(selected_task);
							
						}
					}else {
						this.isWorking = false;
						System.out.println("Thread " + this.toString() + " is being idle.");
					}
					this.isWorking = false;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
}
