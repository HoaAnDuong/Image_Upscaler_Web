package Constant;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
public class Constant {
	public static final String uploadPath = "D:\\java-workspace\\Image-Upscaler-Web\\uploaded";//change this when implementing the program on your device
	public static final String upscaledPath = "D:\\java-workspace\\Image-Upscaler-Web\\upscaled";//change this when implementing the program on your device
	//public static String upscalerPath = "D:\\java-workspace\\Image-Upscaler-Web\\Real-ESRGAN";
	public static final int maxTotalPixels = 300*300;
	public static final int maxFileSize = 1024 * 1024;
	public static final int MAXIMAGESCALED = 15;
	public static final String[] cv2SupportedArray = {"bmp", "dib", "jpeg", "jpg", "jpe", "jp2", "png",
			"pbm", "pgm", "ppm", "sr", "ras", "tiff", "tif"};
	public static final List cv2SupportedList = Arrays.asList(new String []{"bmp", "dib", "jpeg", "jpg", "jpe", "jp2", "png",
		"pbm", "pgm", "ppm", "sr", "ras", "tiff", "tif"});
	public static final HashMap<String,String> getTasknameMap(){
		HashMap<String,String> tasknameMap = new HashMap<String,String>();
		tasknameMap.put("upscale_image_ESRGAN", 
				"python Real-ESRGAN/inference_realesrgan.py -n RealESRGAN_x4plus -i \"uploaded/%s/%s\" -o \"upscaled/%s\" -s %d --fp32");
		tasknameMap.put("upscale_image_ESRGAN_face_enhance", 
				"python Real-ESRGAN/inference_realesrgan.py -n RealESRGAN_x4plus -i \"uploaded/%s/%s\" -o \"upscaled/%s\" -s %d --face_enhance --fp32");
		
		tasknameMap.put("upscale_image_ESRNet", 
				"python Real-ESRGAN/inference_realesrgan.py -n RealESRNet_x4plus -i \"uploaded/%s/%s\" -o \"upscaled/%s\" -s %d --fp32");
		tasknameMap.put("upscale_image_ESRNet_face_enhance", 
				"python Real-ESRGAN/inference_realesrgan.py -n RealESRNet_x4plus -i \"uploaded/%s/%s\" -o \"upscaled/%s\" -s %d --face_enhance --fp32");
		
		tasknameMap.put("upscale_image_ESRGAN_anime", 
				"python Real-ESRGAN/inference_realesrgan.py -n RealESRGAN_x4plus_anime_6B -i \"uploaded/%s/%s\" -o \"upscaled/%s\" -s %d --fp32");
		tasknameMap.put("upscale_image_ESRGAN_anime_face_enhance", 
				"python Real-ESRGAN/inference_realesrgan.py -n RealESRGAN_x4plus_anime_6B -i \"uploaded/%s/%s\" -o \"upscaled/%s\" -s %d --face_enhance --fp32");
		System.out.println("Taskname Map initiated.");
		return tasknameMap;
	}
}
