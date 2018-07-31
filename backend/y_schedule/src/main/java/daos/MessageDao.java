package daos;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.MessageBean;
import util.HibernateUtil;

public class MessageDao {
	public ArrayList<MessageBean> getMessagesByMessageListID(Integer id){
		ArrayList<MessageBean> beans = null;
		Session session = HibernateUtil.getSession();
		Query query;
		String hql;
		
		return beans;
	}
	
}
