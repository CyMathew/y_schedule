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
			case "createMessageList": createMessageList(json, id);
			}
		}
		return messageList;
	}
	
	public JSONArray buildJsonList(JSONObject json, Integer id) {
		list = mld.getMessageListsByUserID(id);
		for(MessageListBean m: list) {
			if(m.getUser1()==id)
			{
				messageList.put(new JSONObject().put("messageListID", (m.getMessage_list_id())).put("user",(m.getUser2())));
			}
			else {

				messageList.put(new JSONObject().put("messageListID", (m.getMessage_list_id())).put("user",(m.getUser1())));
			}
		}
		return messageList;
	}
	
	public JSONArray createMessageList(JSONObject json, Integer id) {
		mld.createNewMessageList(Integer.parseInt((String)json.get("uID1")), Integer.parseInt((String)json.get("uID2")));
		
		return buildJsonList(json,id);
	}
}
