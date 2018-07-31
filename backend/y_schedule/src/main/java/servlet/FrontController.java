package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		

		//ParsedRequest r = JSONHelper.parseAngRequest(request.getReader());
	
		
//		if(r.getUserId() == null) {
//			System.out.println("no user id");
//			response.sendError(404);
//		}
	
		switch (action) {
		case "login":
			rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
			break;
		case "manager":
			rd = request.getRequestDispatcher("ManagerServlet");
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
