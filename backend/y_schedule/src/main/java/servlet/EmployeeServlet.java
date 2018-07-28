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
		EmployeeService empser = new EmployeeService();
		ParsedRequest r = JSONHelper.parseAngRequest(request.getReader());
		JSONHelper.sendResponse(response, empser.parseRequest(tempo, r.getUserId()));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
