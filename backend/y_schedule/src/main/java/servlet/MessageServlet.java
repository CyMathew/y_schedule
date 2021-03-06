package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.ManagerService;
import services.MessageListService;
import services.MessageService2;
import util.JSONHelper;
import util.ParsedRequest;
import util.SessionUtil;

public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MessageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ParsedRequest r = SessionUtil.getParsedRequest(request);

		JSONObject jsonOut = null;

		switch (r.getAction()) {
		case "getMessageListsByID":
			jsonOut = MessageService2.getMessageListsByID(r.getUserId());
			break;
		case "createMessageList":
			jsonOut = MessageService2.createMessageList(r.getUserId(), r.getParameters());
			break;
		case "getMessagesByID":
			jsonOut = MessageService2.getMessagesByListId(r.getUserId(), r.getParameters());
			break;
		case "createMessage":
			jsonOut = MessageService2.createMessage(r.getUserId(), r.getParameters());
			break;
		case "getCoworkers":
			jsonOut = MessageService2.getCoworkers(r.getUserId(), r.getStoreId());
		}
		
		JSONHelper.sendResponse(response, jsonOut);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
