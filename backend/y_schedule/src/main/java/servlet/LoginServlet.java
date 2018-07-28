package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import services.LoginService;
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
		
		JSONObject jsonIn = JSONHelper.parseRequest(request.getReader());
		
		logger.info(jsonIn);
		logger.info("Test ");

		String username = jsonIn.getString("username");
		String action = jsonIn.getString("action");
		
		logger.trace(action);
		logger.trace(username);
		logger.trace(jsonIn.getString("password"));
		
		switch (action) {
		case "login":
			JSONObject jsonOut = LoginService.validateLogin(jsonIn);
			
			if(jsonOut.getString("result").equals("success")) {
				logger.info("LOGIN ID: " + jsonOut.getString("userid"));
			}else {
				logger.info("INVALID LOGIN");
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
