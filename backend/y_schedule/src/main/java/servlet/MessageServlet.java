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

public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MessageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageListService MLS = new MessageListService();
		
		ParsedRequest r = SessionUtil.getParsedRequest(request);
		
		
		JSONObject json = new JSONObject();
		json.put("Messages", MLS.parseRequest(r.getParameters(),Integer.parseInt(r.getParameters().getString("message_list_id"))));
		
		JSONHelper.sendResponse(response, json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
