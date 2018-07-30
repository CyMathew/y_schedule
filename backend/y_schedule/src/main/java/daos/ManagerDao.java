package daos;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.ScheduleTimeBean;
import util.HibernateUtil;
import beans.UserBean;
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
	public List getScheduleByStoreId(Integer id, Timestamp startDate, Timestamp endDate ) {
		Session session = HibernateUtil.getSession();
		
		Query query = session.getNamedQuery("showUserIdAndTime");
		query.setParameter("store_Id", id);
		query.setParameter("start", startDate);
		query.setParameter("end", endDate);
		return query.list();
	}
	public List getScheduleByEmployee(Integer id) {
		Session session = HibernateUtil.getSession();
		
		Query query = session.getNamedQuery("showEmployee");
		query.setParameter("store_Id", id);
		return query.list();
	}
	public ScheduleTimeBean setScheduleByUserId(Timestamp start, Timestamp end, Integer userId) {
		ScheduleTimeBean bean = null;
		Session session = HibernateUtil.getSession();
		
		Query query;
		String hql;
		hql = "UPDATE ScheduleTimeBean SET start = :StartTime,"
				+ "end = :EndTime"
				+ "WHERE user_id = :nuid";
		query = session.createQuery(hql);
		query.setParameter("StartTime", start);
		query.setParameter("EndTime", end);
		query.setParameter("nuid", userId);
		query.executeUpdate();
		return bean;
	} 
	public String createScheduleTimeBean(Integer schId, Timestamp start, Timestamp end, UserBean id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		ScheduleTimeBean bean = new ScheduleTimeBean(schId,start,end,id);
		
		try{
			tx = session.beginTransaction();
			session.save(bean);
			tx.commit();
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
			return "failure";
		}finally{
			session.close();
		}
		return "success";
	}
	
}
