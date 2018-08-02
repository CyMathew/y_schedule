package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import util.JSONHelper;

import util.JSONHelper;
import util.ParsedRequest;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(FrontController.class);
	/**
	 * Default constructor.
	 */
	public FrontController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd;

		//Can only read from request once
		//Store JSON object of parsed request in session for future use
		JSONObject r = JSONHelper.parseRequest(request.getReader());
		HttpSession session = request.getSession();
		session.setAttribute("request", r);
		
		//Get action from URL
		String url = request.getRequestURI();
		String[] tokens = url.split("/");
		String action = tokens[tokens.length - 1];
		action = action.substring(0, action.length() - 3).toLowerCase();
		logger.trace("front controller: " + action);
		
		//redirects to proper servlets; may hit security filters 
		switch (action) {
		case "login":
			rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
			break;
		case "manager":
			rd = request.getRequestDispatcher("manage/ManagerServlet");
			rd.forward(request, response);
			break;
		case "register":
			rd = request.getRequestDispatcher("manage/RegistrationServlet");
			rd.forward(request, response);
			break;
		case "employee":
			rd = request.getRequestDispatcher("employee/EmployeeServlet");
			rd.forward(request, response);
			break;
		case "message":
			rd = request.getRequestDispatcher("user/MessageServlet");
			rd.forward(request, response);
			break;
		case "messageList":
			rd = request.getRequestDispatcher("user/MessageListServlet");
			rd.forward(request, response);
			break;
		default:
			logger.error("hullo? Invalid action");
			response.sendError(404);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
