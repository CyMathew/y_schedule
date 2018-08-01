package services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.MessageListBean;
import daos.MessageListDao;

public class MessageListService {
	private MessageListDao mld = new MessageListDao();
	private ArrayList<MessageListBean>	list = new ArrayList<MessageListBean>();
	private JSONObject reply = new JSONObject();
	private JSONArray messageList = new JSONArray();
	
	public JSONArray parseRequest(JSONObject json, Integer id) {
		list = mld.getMessageListsByUserID(id);
		if(json !=null) {
			switch((String)json.get("action")) {
			
			case "getMessageListsByID": buildJsonList(json,id);
				break;
			case "createMessageList":
			}
		}
		return messageList;
	}
	
	public JSONArray buildJsonList(JSONObject json, Integer id) {
		list = mld.getMessageListsByUserID(id);
		for(MessageListBean m: list) {
			messageList.put(new JSONObject().put("message_list_id", (m.getMessage_list_id())).put("user1",(m.getUser1())).put("user2",  (m.getUser2())));
		}
		return messageList;
	}
	
	public JSONArray addMessageList(JSONObject json, Integer id) {
		mld.createNewMessageList(Integer.parseInt((String)json.get("uID1")), Integer.parseInt((String)json.get("uID2")));
		return buildJsonList(json,id);
	}
}
