package daos;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import beans.ScheduleTimeBean;
import util.HibernateUtil;

public class ManagerDao {
	final static Logger logger = Logger.getLogger(UserDao.class);
	public ScheduleTimeBean getScheduleByUserID(Integer userId) {
		ScheduleTimeBean bean = null;
		Session session = HibernateUtil.getSession();
		
		Query query;
		String hql;
		hql = "FROM ScheduleTimeBean WHERE user_id = :nuid";
		query = session.createQuery(hql);
		query.setParameter("nuid", userId);
		bean = (ScheduleTimeBean)query.uniqueResult();
		return bean;
	}
	public ScheduleTimeBean getScheduleByScheduleID(Integer Schedule_id) {
		ScheduleTimeBean bean = null;
		Session session = HibernateUtil.getSession();
		
		Query query;
		String hql;
		hql = "FROM ScheduleTimeBean WHERE Schedule_id = :nuid";
		query = session.createQuery(hql);
		query.setParameter("nuid", Schedule_id);
		bean = (ScheduleTimeBean)query.uniqueResult();
		return bean;
	}
	public List getScheduleByStoreId(Integer id, Timestamp startTime) {
		Session session = HibernateUtil.getSession();
		
		Query query = session.getNamedQuery("showUserIdAndTime");
		query.setParameter("store_Id", id);
		query.setParameter("start", startTime);
		return query.list();
	}
}
