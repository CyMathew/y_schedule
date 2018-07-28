package daos;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;

import beans.User_TableBeans;
import util.HibernateUtil;

public class User_TableBeansImpl {
	final static Logger logger = Logger.getLogger(User_TableBeansImpl.class);
	/**
	 * This will return one of two strings: "Success" or "Failure"
	 * Success if the password matches the password that is given with the username from the DB
	 * Failure if the password doesn't match the password that is given with the username
	 * */
//	public String checkPassword(String username, String password) {
//		User_TableBeans bean = null;
//		Session session = HibernateUtil.getSession();
//		Query query;
//		String hql;
//		
//		hql = "FROM User_TableBeans WHERE user_username = :nuname";
//		query = session.createQuery(hql);
//		query.setParameter("nuname", username);
//		bean = (User_TableBeans)query.uniqueResult();
//		if (bean == null) {
//			session.close();
//			logger.error("Failure" + username);
//			return "Failure";
//		}
//		else if(bean.getUser_password().equals(password)) {
//			session.close();
//			logger.info("Success" + username +" "+ password);
//			return "Success";
//			}
//		else {
//			session.close();
//			logger.error("Failure" + username +" "+ password);
//			return "Failure";
//		}
//		
//	}
	
	/**
	 *Searches the DB by username to return the role ID that is associated with
	 *the username 
	 **/
	public Integer getSecLvlByUsername(String username) {
		User_TableBeans bean = null;
		Session session = HibernateUtil.getSession();
		Query query;
		String hql;
		hql = "FROM User_TableBeans WHERE user_username = :nuname";
		query = session.createQuery(hql);
		query.setParameter("nuname", username);
		bean = (User_TableBeans)query.uniqueResult();
		logger.info("UserName" + username);
		if(bean != null) {
			return bean.getSec_lvl();
		}else return 0;
	}

	
	public User_TableBeans getSecLvlByUsernameAndPassword(String username, String password) {
		User_TableBeans bean = null;
		Session session = HibernateUtil.getSession();
		
		Query query;
		String hql;
		hql = "FROM User_TableBeans WHERE user_username = :nuname";
		query = session.createQuery(hql);
		query.setParameter("nuname", username);
		bean = (User_TableBeans)query.uniqueResult();

		return bean;		
	}


	public User_TableBeans getUserById(Integer userId) {
		User_TableBeans bean = null;
		Session session = HibernateUtil.getSession();
		
		Query query;
		String hql;
		hql = "FROM User_TableBeans WHERE user_id = :nuid";
		query = session.createQuery(hql);
		query.setParameter("nuid", userId);
		bean = (User_TableBeans)query.uniqueResult();

		return bean;
	}
}
