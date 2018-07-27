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
	public String checkPassword(String username, String password) {
		User_TableBeans bean = null;
		Session session = HibernateUtil.getSession();
		Query query;
		String hql;
		
		hql = "FROM User_TableBeans WHERE user_username = :nuname";
		query = session.createQuery(hql);
		query.setParameter("nuname", username);
		bean = (User_TableBeans)query.uniqueResult();
		if (bean == null) {
			session.close();
			logger.error("Failure" + username);
			return "Failure";
		}
		else if(bean.getUser_password().equals(password)) {
			session.close();
			logger.info("Success" + username +" "+ password);
			return "Success";
			}
		else {
			session.close();
			logger.error("Failure" + username +" "+ password);
			return "Failure";
		}
		
	}
	
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
		return bean.getSec_lvl();
	}

	
	public JSONObject getSecLvlByUsernameAndPassword(String username, String password) {
		User_TableBeans bean = null;
		Session session = HibernateUtil.getSession();
		JSONObject tempo = new JSONObject();
		Query query;
		String hql;
		hql = "FROM User_TableBeans WHERE user_username = :nuname";
		query = session.createQuery(hql);
		query.setParameter("nuname", username);
		bean = (User_TableBeans)query.uniqueResult();
		if(password.equals(bean.getUser_password())){
			switch(bean.getSec_lvl()) {
			case 1: 
				tempo.put("result", "success");
				tempo.put("role", "employee");
				logger.info("Case 1" + tempo);
				return tempo;
			case 2:
				tempo.put("result", "success");
				tempo.put("role", "manager");
				logger.info("Case 2" + tempo);
				return tempo;
			case 3:	
				tempo.put("result", "success");
				tempo.put("role", "coordinator");
				logger.info("Case 3" + tempo);
				return tempo;
			}
		}
		
		tempo.put("result", "failure");
		return tempo;		
	}
}
