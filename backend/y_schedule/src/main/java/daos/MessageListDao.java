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

	public ArrayList<MessageListBean> getMessageListsByUserID(UserBean id){

		Session session = HibernateUtil.getSession();
		ArrayList<MessageListBean> messages;
		
		Query query;
		String hql;
		hql = "FROM MessageListBean WHERE user1 = :nuid1 OR user2 = :nuid2";
		query = session.createQuery(hql);
		query.setParameter("nuid1", id);
		query.setParameter("nuid2", id);
		messages = (ArrayList<MessageListBean>)query.list();

		return messages;
	}
	
	public MessageListBean createNewMessageList(UserBean uID, UserBean uID2) {
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
			return null;
		}finally {
			session.close();
		}
		
		return newMessageList;
	}
	
	public MessageListBean getMessageListById(Integer id) {
		MessageListBean mlb = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM MessageListBean WHERE message_list_id = :nid");
			query.setParameter("nid", id);
			mlb = (MessageListBean) query.uniqueResult();
			
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

	public MessageListBean getMessageListByUsers(UserBean userId1, UserBean userId2) {
		MessageListBean mlb = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM MessageListBean WHERE (user1 = :nid1 AND user2 = :nid2) OR (user2 = :nid1 AND user1 = :nid2)");
			query.setParameter("nid1", userId1);
			query.setParameter("nid2", userId2);
			mlb = (MessageListBean) query.uniqueResult();
			
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
