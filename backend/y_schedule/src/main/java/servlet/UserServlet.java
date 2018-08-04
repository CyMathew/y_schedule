package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import services.ManagerService;
import util.JSONHelper;
import util.ParsedRequest;
import util.SessionUtil;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final Logger logger = Logger.getLogger(LoginServlet.class);

		ParsedRequest r = SessionUtil.getParsedRequest(request);//JSONHelper.parseAngRequest(request.getReader());

		logger.info("Manager userId: " + r.getUserId());
		logger.info("ManagerServlet Action: " + r.getAction());

		JSONObject jsonOut = null;
		
		switch (r.getAction()) {
		case "viewHome":
			jsonOut = ManagerService.selectEmpInfo(r.getUserId());
			break;
		}
		
		JSONHelper.sendResponse(response, jsonOut);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
