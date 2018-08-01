package services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.MessageBean;
import daos.MessageDao;

public class MessageService {
	private MessageDao mld = new MessageDao();
	private ArrayList<MessageBean>	list = new ArrayList<MessageBean>();
	private JSONObject reply = new JSONObject();
	private JSONArray messageList = new JSONArray();
	
	public JSONArray parseRequest(JSONObject json, Integer id) {
		list = mld.getMessageListsByUserID(id);
		if(json !=null) {
			switch((String)json.get("action")) {
			
			case "getMessagesByID": buildJsonList(json,id);
				break;
			case "createMessages":
			}
		}
		return messageList;
	}
	
	public JSONArray buildJsonList(JSONObject json, Integer id) {
		list = mld.getMessageListsByUserID(id);
		for(MessageBean m: list) {
			messageList.put(new JSONObject().put("message_list_id", (m.getMessage_list_id())).put("user1",(m.getUser1())).put("user2",  (m.getUser2())));
		}
		return messageList;
	}
	
	public JSONArray addMessages(JSONObject json, Integer id) {
		mld.createNewMessageList(Integer.parseInt((String)json.get("uID1")), Integer.parseInt((String)json.get("uID2")));
		return buildJsonList(json,id);
	}
}
