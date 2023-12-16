import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

import org.json.simple.JSONObject;
import Constant.Constant;

public class foo {
	
//	private static void executeShell(String directory,String... commands) throws Exception {
//		ProcessBuilder pb 
//        = new ProcessBuilder(commands); 
//	    // Exception thrown here because folder 
//	    // structure of Windows and Linux are different. 
//	    pb.directory( 
//	        new File(directory)); 
//	    // It will throw and exception 
//	    Process process = pb.start(); 
//	
//	    StringBuilder output = new StringBuilder(); 
//	    BufferedReader reader 
//	        = new BufferedReader(new InputStreamReader( 
//	            process.getInputStream())); 
//	
//	    String line; 
//	    while ((line = reader.readLine()) != null) { 
//	    	System.out.println(line); 
//	    } 
//	
//	    int exitVal = process.waitFor(); 
//	    
//	}
	private static void executeShell(String command) throws Exception {
		try {
				LocalDateTime ldt1 = LocalDateTime.now();
				System.out.println(ldt1);
            
	        // run the Unix "ps -ef" command
	            // using the Runtime exec method:
				System.out.println(command);
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
	            
	            
	            
	            
	            p.waitFor();
	            //System.exit(0);
	            LocalDateTime ldt2 = LocalDateTime.now();
	            
	            System.out.println(ldt2);
	            
	        }
	        catch (IOException e) {
	            System.out.println("exception happened - here's what I know: ");
	            e.printStackTrace();
	            System.exit(-1);
	        }
	}
	public static void main(String args[]) throws Exception {
		
		//executeShell(Constant.upscalerPath,"python", "inference_realesrgan.py", "-n", "RealESRGAN_x4plus" , "-i", "inputs/beach.jfif", "-o", "../outputs", "-s", "2", "--fp32");
		
		//executeShell("python Real-ESRGAN/inference_realesrgan.py -n RealESRGAN_x4plus -i \"uploaded/hoaan123/(2) asian-girl.jpg\" -o \"upscaled/hoaan123\" -s 2 --face_enhance --fp32");
		//executeShell("python Real-ESRGAN/inference_realesrgan.py -n RealESRNet_x4plus -i \"uploaded/hoaan123/(2) asian-girl.jpg\" -o \"upscaled/hoaan123\" -s 2 --face_enhance --fp32");
		//executeShell("python Real-ESRGAN/inference_realesrgan.py -n RealESRGAN_x4plus_anime_6B -i \"uploaded/hoaan123/(2) asian-girl.jpg\" -o \"upscaled/hoaan123\" -s 2 --face_enhance --fp32");
		
	}
	
}
