package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import daos.User_TableBeansImpl;
import util.JSONHelper;

/**
 * Servlet implementation class testservlet
 */
public class testservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public testservlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User_TableBeansImpl implbean = new User_TableBeansImpl();
		HttpSession session = request.getSession();
		JSONObject temp = JSONHelper.parseRequest(request.getReader());

		String username = null;
		String password = null;
		String action = null;
		username = temp.getString("username");
		password = temp.getString("password"); 
		action = temp.getString("action");
		
		switch(action) {
		case "login": 	JSONHelper.sendResponse(response, implbean.getSecLvlByUsernameAndPassword(username, password));
						break;
						
		case "logout":

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
