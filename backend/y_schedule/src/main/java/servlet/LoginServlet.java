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

import daos.User_TableBeansImpl;
import service.LoginService;
import util.HtmlTemplates;
import util.JSONHelper;

/**
 * Servlet implementation class testservlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public LoginServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final Logger logger = Logger.getLogger(LoginServlet.class);
		
		HttpSession session = request.getSession();
		JSONObject jsonIn = JSONHelper.parseRequest(request.getReader());
		PrintWriter out = response.getWriter();
		
		logger.info(jsonIn);

		logger.info("Test ");

		String username = jsonIn.getString("username");
		String action = jsonIn.getString("action");
		
		logger.info(action);
		logger.info(username);

		switch (action) {
		case "login":
			JSONObject jsonOut = LoginService.validateLogin(jsonIn);
			
			if(jsonOut.getString("result").equals("success")) {
				session.setAttribute("userid", (Integer)jsonOut.get("userid"));
				session.setAttribute("username", username);
				session.setAttribute("userrole", (Integer)jsonOut.get("role"));
				logger.info("LOGIN STARTED: " + (String)session.getAttribute("username"));
			}else {
				logger.info("invalid login");
			}
			JSONHelper.sendResponse(response, jsonOut);
			
			break;

		case "logout":
			logger.info("attempt logout");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
