package services;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.MessageBean;
import daos.MessageDao;

public class MessageService {
	private MessageDao mld = new MessageDao();
	private ArrayList<MessageBean>	list = new ArrayList<MessageBean>();
	private JSONObject reply = new JSONObject();
	private JSONArray message = new JSONArray();
	
	public JSONArray parseRequest(JSONObject json, Integer id) {
		list = mld.getMessagesByListID(id);
		if(json !=null) {
			switch((String)json.get("action")) {
			
			case "getMessagesByID": buildJsonList(json,id);
				break;
			case "createMessage": addMessage(json, id);
			}
		}
		return message;
	}
	
	public JSONArray buildJsonList(JSONObject json, Integer id) {
		list = mld.getMessagesByListID(id);
		for(MessageBean m: list) {
			message.put(new JSONObject().put("userID", (m.getuID())).put("message",(m.getMessage())).put("timestamp",  (m.getSentTime())));
		}
		return message;
	}
	
	public JSONArray addMessage(JSONObject json, Integer id) {
		mld.createNewMessage(id, (String)json.get("message"), (Timestamp)json.get("timestamp"));
		return buildJsonList(json,id);
	}
}
