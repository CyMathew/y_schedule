package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.MessageListService;
import util.JSONHelper;
import util.ParsedRequest;
import util.SessionUtil;

public class MessageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MessageListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageListService MLS = new MessageListService();
		
		ParsedRequest r = SessionUtil.getParsedRequest(request);
		
		JSONObject json = new JSONObject();
		json.put("messageList", MLS.parseRequest(r.getParameters(), r.getUserId()));
		
		JSONHelper.sendResponse(response, json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
