package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import util.JSONHelper;
import util.ParsedRequest;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		String url = request.getRequestURI();

		//System.out.println(url);

		String[] tokens = url.split("/");
		String action = tokens[tokens.length - 1];

		action = action.substring(0, action.length() - 3).toLowerCase();
		System.out.println("front controller: " + action);
		
		JSONObject r = JSONHelper.parseRequest(request.getReader());
		System.out.println(r);
		HttpSession session = request.getSession();
		session.setAttribute("request", r);
		
		switch (action) {
		case "login":
			rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
			break;
		case "manager":
			rd = request.getRequestDispatcher("manage/ManagerServlet");
			rd.forward(request, response);
			break;
		default:
			System.out.println("hullo?");
			response.sendError(404);
		}

		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
