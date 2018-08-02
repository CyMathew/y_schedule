package services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.MessageBean;
import beans.MessageListBean;
import beans.UserBean;
import daos.MessageDao;
import daos.MessageListDao;
import daos.UserDao;

public class MessageService2 {
	
	public static JSONObject getMessageListsByID(Integer userId) {
		UserBean b = new UserDao().getUserById(userId);

		return getMessageListsByID(b);
	}

	public static JSONObject getMessageListsByID(UserBean userBean) {
		MessageListDao mld = new MessageListDao();

		System.out.println("getMessageListsByID - userId: " + userBean);

		ArrayList<MessageListBean> list = mld.getMessageListsByUserID(userBean);
		JSONArray messageList = new JSONArray();

		for (MessageListBean m : list) {
			System.out.println(m);
			JSONObject mess = new JSONObject();
			mess.put("messageListID", (m.getMessage_list_id()));

			UserBean otherUser;
			
			if (m.getUser1().getUser_id() == userBean.getUser_id())
				otherUser = m.getUser2();
			else
				otherUser = m.getUser1();

			mess.put("userId", otherUser.getUser_id());
			mess.put("userName", UserService.getUserName(otherUser));
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

		MessageListBean b = mld.createNewMessageList(userbean, userbeanOther);

		return new JSONObject().put("messageListId", b.getMessage_list_id());
	}

	public static JSONObject getMessagesByListId(Integer userId, JSONObject parameters) {

		Integer messageListId = Integer.parseInt(parameters.getString("messageListId"));

		return getMessagesByListId(userId, messageListId);
	}

	public static JSONObject getMessagesByListId(Integer userId, Integer messageListId) {
		ArrayList<MessageBean> list = new ArrayList<MessageBean>();
		MessageDao mld = new MessageDao();
		JSONArray messages = new JSONArray();

		list = mld.getMessagesByListID(messageListId);

		for (MessageBean m : list) {
			JSONObject json = new JSONObject();
			json.put("userID", (m.getuserID()));
			json.put("message", (m.getMessage()));
			json.put("timestamp", (m.getSentTime()));
			messages.put(json);
		}
		
		UserBean otherUser = conversationGetOther(userId, messageListId);
		
		JSONObject jsonOut = new JSONObject();
		jsonOut.put("messages", messages);
		jsonOut.put("otherName", UserService.getUserName(otherUser));
		jsonOut.put("otherId", otherUser.getUser_id());
		
		return jsonOut;
	}

	private static UserBean conversationGetOther(Integer userId, Integer messageListId) {
		
		System.err.println("userId: " + userId);
		System.err.println("messageListId: " + messageListId);
		
		MessageListBean mlb = new MessageListDao().getMessageListById(messageListId);
		
		if(mlb.getUser1().getUser_id() == userId)
			return mlb.getUser2();
		else
			return mlb.getUser1();
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

		return getMessagesByListId(userId, messageListId);
	}

	public static JSONObject getCoworkers(Integer userId, Integer storeId) {

		List<UserBean> coworkers = new UserDao().getUsersByStoreId(storeId);

		JSONArray coworkerArray = new JSONArray();

		for (UserBean b : coworkers) {
			if (b.getUser_id() == userId)
				continue;
			JSONObject json = new JSONObject();
			json.put("name", UserService.getUserName(b));
			json.put("userId", b.getUser_id());
			coworkerArray.put(json);
		}

		return new JSONObject().put("coworkers", coworkerArray);
	}

}
