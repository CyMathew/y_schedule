package daos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;

import beans.MessageBean;
import beans.MessageListBean;
import beans.UserBean;
import util.HibernateUtil;

public class MessageDao {
	public ArrayList<MessageBean> getMessagesByListID(Integer mlb){
		ArrayList<MessageBean> messages = new ArrayList<MessageBean>();
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(MessageBean.class);
		List<MessageBean> list1 = crit.add(Restrictions.like("messageListID.message_list_id", mlb)).list();
		for(MessageBean m: list1) {
			messages.add(m);
		}
		session.close();
		return messages;
	}
	
	public String createNewMessage(UserBean userID, String message, Timestamp time, MessageListBean messageListID) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			MessageBean newMessage = new MessageBean(userID,message,time, messageListID);
			session.save(newMessage);
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
			return "failure";
		}finally {
			session.close();
		}
		
		return "success";
	}
	

}
