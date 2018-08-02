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
import util.HibernateUtil;

public class MessageDao {
	public ArrayList<MessageBean> getMessagesByListID(Integer id){
		ArrayList<MessageBean> messages = new ArrayList<MessageBean>();
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(MessageListBean.class);
		List<MessageBean> list1 = crit.add(Restrictions.like("Message.message_list_id", id)).list();
		for(MessageBean m: list1) {
			messages.add(m);
		}
		session.close();
		return messages;
	}
	
	public String createNewMessage(Integer uID, String message, Timestamp time, MessageListBean messageListID) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		MessageBean newMessage = new MessageBean(uID,message,time, messageListID);
		try {
			tx = session.beginTransaction();
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
