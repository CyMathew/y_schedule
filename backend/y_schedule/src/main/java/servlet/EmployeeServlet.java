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
import util.SessionUtil;


public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EmployeeServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	System.err.println("I've hit the servlet");
		EmployeeService empser = new EmployeeService();

		//Get the request message
		ParsedRequest r = SessionUtil.getParsedRequest(request);

		System.err.println(r);

		//SEnds the proper response JSON with the JSON request and the Cookie for the ID
		JSONHelper.sendResponse(response, empser.parseRequest(r.getParameters(), r.getUserId()));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
