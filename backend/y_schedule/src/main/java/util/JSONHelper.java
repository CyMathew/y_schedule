package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONHelper
{
    public static JSONObject parseRequest(BufferedReader reader) throws IOException
    {
        JSONTokener jsonTokener = new JSONTokener(reader);
        JSONObject jsonObject = null;
        if(jsonTokener.more())
            jsonObject = new JSONObject(jsonTokener);

        return jsonObject;
    }

    public static void sendResponse(HttpServletResponse resp, JSONObject jsonObject) throws IOException
    {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        out.println(jsonObject);
    }
    
    public static ParsedRequest parseAngRequest(BufferedReader reader) throws IOException
    {
    	JSONObject jsonObject = parseRequest(reader);
    	
    	Integer userId = null;
    	if(jsonObject.getString("userid") != null)
    		userId = Integer.parseInt(jsonObject.getString("userid"));
    	
    	JSONObject parameters = jsonObject.getJSONObject("param");
    	
    	return new ParsedRequest(userId, parameters);
    	
    }
}
