package services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.MessageListBean;
import beans.UserBean;
import daos.MessageListDao;
import daos.UserDao;

public class MessageService2 {

	public static JSONObject getMessageListsByID(Integer userId) {
		MessageListDao mld = new MessageListDao();
		ArrayList<MessageListBean> list = mld.getMessageListsByUserID(userId);
		JSONArray messageList = new JSONArray();

		for (MessageListBean m : list) {
			JSONObject mess = new JSONObject();
			mess.put("messageListID", (m.getMessage_list_id()));

			if (m.getUser1() == userId)
				mess.put("userId", m.getUser2());
			else
				mess.put("userId", m.getUser1());
		}

		return new JSONObject().put("messageList", messageList);
	}

	public static JSONObject createMessageList(Integer userId, JSONObject parameters) {
		Integer otherUser = Integer.parseInt(parameters.getString("otherId"));
		return createMessageList(userId, otherUser);
	}

	public static JSONObject createMessageList(Integer userId, Integer otherUser) {
		MessageListDao mld = new MessageListDao();
		mld.createNewMessageList(userId, otherUser);
		
		return getMessageListsByID(userId);
	}

	public static JSONObject getMessagesByID(Integer userId, JSONObject parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	public static JSONObject getMessagesByID(Integer userId, Integer messageListId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static JSONObject createMessage(Integer userId, JSONObject parameters) {
		
		
		return null;
	}

	public static JSONObject createMessage(Integer userId, Integer messageListId, String message) {
		
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		
		MessageListBean mlb = new MessageListDao().getMessageListById(messageListId);
		UserBean user = new UserDao().getUserById(userId);
		
		new MessageDao().createNewMessage(user, message, now, mlb);
		return null;
	}

}
