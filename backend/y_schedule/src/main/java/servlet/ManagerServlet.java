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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final Logger logger = Logger.getLogger(LoginServlet.class);

		ParsedRequest r = JSONHelper.parseAngRequest(request.getReader());

		logger.info("Manager userId: " + r.getUserId());
		logger.info("ManagerServlet Action: " + r.getAction());

		JSONObject jsonOut = null;

		switch (r.getAction()) {
		case "viewHome":
			jsonOut = ManagerService.selectEmpInfo(r.getUserId());
			break;
		case "viewSchedule":
			jsonOut = ManagerService.selectScheduledTimesByWeek(r.getStoreId(), r.getParameters());
			break;
		}
		
		JSONHelper.sendResponse(response, jsonOut);
		logger.info("JSON Object Sent");
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
