package daos;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
	public List<ScheduleTimeBean> getScheduleByStoreId(Integer id, Timestamp startTime, Timestamp endTime) {
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(ScheduleTimeBean.class);
		List<ScheduleTimeBean> list = crit
				.add(Restrictions.between("startTime", startTime, endTime)).list();
		//.add(Restrictions.like("users.store_Id", id))
		Transaction tx = null;
		return list;
	}
	public List<ScheduleTimeBean> getScheduleByEmployee(Integer id, Timestamp startTime, Timestamp endTime) {
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(ScheduleTimeBean.class);
		List<ScheduleTimeBean> list = crit.add(Restrictions.like("users.user_id", id))
				.add(Restrictions.between("startTime", startTime, endTime)).list();
		Transaction tx = null;
		
		return list;
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
