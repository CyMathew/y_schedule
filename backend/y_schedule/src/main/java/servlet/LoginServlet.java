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
		User_TableBeansImpl implbean = new User_TableBeansImpl();
		HttpSession session = request.getSession();
		JSONObject temp = JSONHelper.parseRequest(request.getReader());
		PrintWriter out = response.getWriter();
		
		logger.info(temp);

		logger.info("Test ");

		String username = null;
		String password = null;
		String action = null;
		username = temp.getString("username");
		password = temp.getString("password");
		action = temp.getString("action");
		
		logger.info(action);
		logger.info(username);
		logger.info(password);
		
		

		switch (action) {
		case "login":
			JSONObject loginObj = implbean.getSecLvlByUsernameAndPassword(username, password);
			
			if(loginObj.getString("result").equals("success")) {
				session.setAttribute("userid", (Integer)loginObj.get("userid"));
				session.setAttribute("username", username);
			}
			
			JSONHelper.sendResponse(response, loginObj);
			logger.info("LOGIN STARTED: " + (String)session.getAttribute("username"));
			break;

		case "logout":
			logger.info("Loging is Failure");
			HtmlTemplates.goBackButton(out);
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
