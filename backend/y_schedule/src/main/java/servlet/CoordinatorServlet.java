package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.CoordinatorService;
import util.JSONHelper;
import util.SessionUtil;


public class CoordinatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CoordinatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CoordinatorService coordinatorservice = new CoordinatorService();
		JSONObject jsonrequest = SessionUtil.getParsedRequest(request).getParameters();
		JSONHelper.sendResponse(response, coordinatorservice.gateKeeper(jsonrequest));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
