package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.EmployeeService;
import util.JSONHelper;
import util.ParsedRequest;


public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EmployeeServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject tempo = JSONHelper.parseRequest(request.getReader());
		System.out.println(tempo);
		EmployeeService empser = new EmployeeService();
		
		//Get the request message
		ParsedRequest r = JSONHelper.parseAngRequest(request.getReader());

		//SEnds the proper response JSON with the JSON request and the Cookie for the ID
//		System.out.println(r.getUserId());
//		int i = Integer.parseInt(tempo.getJSONObject("sessionData").getString("userid"));
		JSONHelper.sendResponse(response, empser.parseRequest(tempo.getJSONObject("param"), 23));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
