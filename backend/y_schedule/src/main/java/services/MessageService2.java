package services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.MessageBean;
import beans.MessageListBean;
import beans.UserBean;
import daos.MessageDao;
import daos.MessageListDao;
import daos.UserDao;

public class MessageService2 {

	public static JSONObject getMessageListsByID(UserBean userId) {
		MessageListDao mld = new MessageListDao();
		
		System.out.println("getMessageListsByID - userId: " + userId);
		
		ArrayList<MessageListBean> list = mld.getMessageListsByUserID(userId);
		JSONArray messageList = new JSONArray();

		
		
		for (MessageListBean m : list) {
			System.out.println(m);
			JSONObject mess = new JSONObject();
			mess.put("messageListID", (m.getMessage_list_id()));

			if (m.getUser1() == userId)
				mess.put("userId", m.getUser2());
			else
				mess.put("userId", m.getUser1());
			
			messageList.put(mess);
		}

		return new JSONObject().put("messageList", messageList);
	}

	public static JSONObject createMessageList(Integer userId, JSONObject parameters) {
		Integer otherUser = Integer.parseInt(parameters.getString("otherId"));
		return createMessageList(userId, otherUser);
	}

	public static JSONObject createMessageList(Integer userId, Integer otherUser) {
		MessageListDao mld = new MessageListDao();
		UserBean userbean = new UserDao().getUserById(userId);
		UserBean userbeanOther = new UserDao().getUserById(otherUser);
		
		mld.createNewMessageList(userbean, userbeanOther);

		return getMessageListsByID(userbean);
	}

	public static JSONObject getMessagesByID(Integer userId, JSONObject parameters) {

		Integer messageListId = Integer.parseInt(parameters.getString("messageListId"));

		return getMessagesByID(userId, messageListId);
	}

	public static JSONObject getMessagesByID(Integer userId, Integer messageListId) {
		ArrayList<MessageBean> list = new ArrayList<MessageBean>();
		MessageDao mld = new MessageDao();
		JSONArray messages = new JSONArray();

		list = mld.getMessagesByListID(userId);

		for (MessageBean m : list) {
			messages.put(new JSONObject().put("userID", (m.getuserID())).put("message", (m.getMessage()))
					.put("timestamp", (m.getSentTime())));
		}

		return new JSONObject().put("messages", messages);
	}

	public static JSONObject createMessage(Integer userId, JSONObject parameters) {

		Integer messageListId = Integer.parseInt(parameters.getString("messageListId"));
		String message = parameters.getString("message");

		return createMessage(userId, messageListId, message);
	}

	public static JSONObject createMessage(Integer userId, Integer messageListId, String message) {

		Timestamp now = Timestamp.valueOf(LocalDateTime.now());

		MessageListBean mlb = new MessageListDao().getMessageListById(messageListId);
		UserBean user = new UserDao().getUserById(userId);

		new MessageDao().createNewMessage(user, message, now, mlb);

		return getMessagesByID(userId, messageListId);
	}

	public static JSONObject getMessageListsByID(Integer userId) {
		UserBean b = new UserDao().getUserById(userId);
		
		return getMessageListsByID(b);
	}

}
