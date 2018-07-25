package daos;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import beans.User_TableBeans;
import util.HibernateUtil;

public class User_TableBeansImpl {
	
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
		if(bean.getUser_password().equals(password)) {
			session.close();
			return "Success";
			}
		else {
			session.close();
			return "Failure";
		}
		
	}

}
