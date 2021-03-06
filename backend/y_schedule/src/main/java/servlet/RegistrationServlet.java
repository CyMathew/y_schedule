package servlet;

import java.io.IOException;

import javax.imageio.spi.RegisterableService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import services.RegistrationService;
import util.JSONHelper;
import util.SessionUtil;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Got the post request");
		JSONObject temp = SessionUtil.getParsedRequest(request).getParameters();
		
		RegistrationService regiser = new RegistrationService();
		HttpSession session = request.getSession();
		JSONObject jsonreply = regiser.CreateNewEmployee(temp.getString("first_name"), temp.getString("last_name"), temp.getString("username"), temp.getString("password"),(Integer)session.getAttribute("storeid"));
		JSONHelper.sendResponse(response, jsonreply);
	}

}
