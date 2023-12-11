package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class RequestUtil {
	public static JSONObject resolveRequestJSON(HttpServletRequest request) throws Exception {
		Class.forName("org.json.simple.JSONObject");
		Class.forName("org.json.simple.parser.JSONParser");
		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	      sb.append(line);
	    }
	    String requestBody = sb.toString();
	    return (JSONObject) new JSONParser().parse(requestBody);
	}
	public static String convertPartToString(Part part) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
	    StringBuilder stringBuilder = new StringBuilder();
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	        stringBuilder.append(line);
	    }
	    return stringBuilder.toString();
	}
}
