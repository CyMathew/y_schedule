package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import beans.User_TableBeans;
import service.EmployeeService;
import service.ManagerService;
import util.JSONHelper;

/**
 * Servlet implementation class managerServlet
 */
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Logger logger = Logger.getLogger(LoginServlet.class);
		HttpSession session = request.getSession();
		JSONObject jsonIn = JSONHelper.parseRequest(request.getReader());	
		
		Integer userId = (Integer)session.getAttribute("userid");
		System.out.println(userId);
		JSONObject jsonOut = ManagerService.selectEmpInfo(jsonIn, userId);
		
		JSONHelper.sendResponse(response, jsonOut);
		logger.info("JSON Object Sent");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
