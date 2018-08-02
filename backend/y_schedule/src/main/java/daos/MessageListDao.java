package daos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import beans.MessageBean;
import beans.MessageListBean;
import beans.UserBean;
import util.HibernateUtil;

public class MessageListDao {

	public ArrayList<MessageListBean> getMessageListsByUserID(Integer id){
//		ArrayList<MessageListBean> messages = new ArrayList<MessageListBean>();
		Session session = HibernateUtil.getSession();
		ArrayList<MessageListBean> messages;
		
		Query query;
		String hql;
		hql = "FROM MessageListBean WHERE user1 = :nuid1 OR user2 = :nuid2";
		query = session.createQuery(hql);
		query.setParameter("nuid1", id);
		query.setParameter("nuid2", id);
		messages = (ArrayList<MessageListBean>)query.list();

//		Criteria crit = session.createCriteria(MessageListBean.class);
//		List<MessageListBean> list1 = crit.add(Restrictions.like("user1", id)).list();
//		List<MessageListBean> list2 = crit.add(Restrictions.like("user2", id)).list();
//		for(MessageListBean m: list1) {
//			messages.add(m);
//		}
//		for(MessageListBean m: list2) {
//			messages.add(m);
//		}
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
	
	public MessageListBean getMessageListById(Integer id) {
		MessageListBean mlb = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mlb = (MessageListBean) session.createQuery("FROM MessageList WHERE message_list_id = " + id)
					.uniqueResult();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close(); // persons is now in the detached state.
		}
		return mlb;
	}
}
