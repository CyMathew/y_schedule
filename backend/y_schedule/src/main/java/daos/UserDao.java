package daos;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import beans.UserBean;
import util.HibernateUtil;

public class UserDao {
	final static Logger logger = Logger.getLogger(UserDao.class);
	/**
	 * This will return one of two strings: "Success" or "Failure"
	 * Success if the password matches the password that is given with the username from the DB
	 * Failure if the password doesn't match the password that is given with the username
	 * */
//	public String checkPassword(String username, String password) {
//		UserBean bean = null;
//		Session session = HibernateUtil.getSession();
//		Query query;
//		String hql;
//		
//		hql = "FROM UserBean WHERE user_username = :nuname";
//		query = session.createQuery(hql);
//		query.setParameter("nuname", username);
//		bean = (UserBean)query.uniqueResult();
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
		UserBean bean = null;
		Session session = HibernateUtil.getSession();
		Query query;
		String hql;
		hql = "FROM UserBean WHERE user_username = :nuname";
		query = session.createQuery(hql);
		query.setParameter("nuname", username);
		bean = (UserBean)query.uniqueResult();
		logger.info("UserName" + username);
		if(bean != null) {
			return bean.getSec_lvl();
		}else return 0;
	}

	
	public UserBean getSecLvlByUsernameAndPassword(String username, String password) {
		UserBean bean = null;
		Session session = HibernateUtil.getSession();
		
		Query query;
		String hql;
		hql = "FROM UserBean WHERE user_username = :nuname";
		query = session.createQuery(hql);
		query.setParameter("nuname", username);
		bean = (UserBean)query.uniqueResult();

		return bean;		
	}


	public UserBean getUserById(Integer userId) {
		UserBean bean = null;
		Session session = HibernateUtil.getSession();
		
		Query query;
		String hql;
		hql = "FROM UserBean WHERE user_id = :nuid";
		query = session.createQuery(hql);
		query.setParameter("nuid", userId);
		bean = (UserBean)query.uniqueResult();

		return bean;
	}


	public List<UserBean> getUsersByStoreId(Integer storeId) {
		List<UserBean> beans = null;
		Session session = HibernateUtil.getSession();
		
		Query query;
		String hql;
		hql = "FROM UserBean WHERE store_id = :nsid";
		query = session.createQuery(hql);
		query.setParameter("nsid", storeId);
		beans = query.list();
		
		return beans;
	}
}
