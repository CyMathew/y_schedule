package daos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import beans.MessageBean;
import beans.MessageListBean;
import util.HibernateUtil;

public class MessageListDao {

	public ArrayList<MessageListBean> getMessageListsByUserID(Integer id){
		ArrayList<MessageListBean> messages = new ArrayList<MessageListBean>();
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(MessageListBean.class);
		List<MessageListBean> list1 = crit.add(Restrictions.like("MessageList.user1", id)).list();
		List<MessageListBean> list2 = crit.add(Restrictions.like("MessageList.user2", id)).list();
		for(MessageListBean m: list1) {
			messages.add(m);
		}
		for(MessageListBean m: list2) {
			messages.add(m);
		}
		return messages;
	}
	
	public String createNewMessageList(Integer uID, Integer uID2) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		MessageListBean newMessageList = new MessageListBean(uID,uID2);
		try {
			tx = session.beginTransaction();
			session.save(newMessageList);
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
