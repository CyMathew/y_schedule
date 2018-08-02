package services;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

import beans.MessageBean;
import beans.MessageListBean;
import daos.MessageDao;
import util.HibernateUtil;

public class MessageService {
	private MessageDao mld = new MessageDao();
	private ArrayList<MessageBean> list = new ArrayList<MessageBean>();
	private JSONObject reply = new JSONObject();
	private JSONArray message = new JSONArray();

	public JSONArray parseRequest(JSONObject json, Integer id) {
		list = mld.getMessagesByListID(id);
		if (json != null) {
			switch ((String) json.get("action")) {

			case "getMessagesByID":
				buildJsonList(json, id);
				break;
			case "createMessage":
				//addMessage(json, id);
				break;
			}
		}
		return message;
	}

	public JSONArray buildJsonList(JSONObject json, Integer id) {
		list = mld.getMessagesByListID(id);
		for (MessageBean m : list) {
			message.put(new JSONObject().put("userID", (m.getuserID())).put("message", (m.getMessage()))
					.put("timestamp", (m.getSentTime())));
		}
		return message;
	}

//	public JSONArray addMessage(JSONObject json, Integer id) {
//		MessageListBean mlb = null;
//		Session session = HibernateUtil.getSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			mlb = (MessageListBean) session.createQuery("FROM MessageList WHERE message_list_id = " + id)
//					.uniqueResult();
//		} catch (HibernateException e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//		} finally {
//			session.close(); // persons is now in the detached state.
//		}
//
//		mld.createNewMessage(Integer.parseInt((String) json.get("user")), (String) json.get("message"),
//				(Timestamp) json.get("timestamp"), mlb);
//		return buildJsonList(json, id);
//	}

}
