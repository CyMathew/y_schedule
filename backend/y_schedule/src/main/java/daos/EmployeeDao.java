package daos;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import beans.EmployeeAvailabilityBean;
import beans.ScheduleTimeBean;
import beans.UserBean;
import util.HibernateUtil;

public class EmployeeDao {

	/**
	 * Removes old records of employee availability returns true if success false if
	 * fail
	 */
	public boolean removeAllReguests(Integer id) {
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(EmployeeAvailabilityBean.class);
		List<EmployeeAvailabilityBean> list = crit.add(Restrictions.like("user.user_id", id)).list();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (EmployeeAvailabilityBean i : list) {
				i.setActive(0);
				session.delete(i);
			}
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	/**
	 * returns a string if the table was successfully updated with the new
	 * availability times
	 */
	public String updateRequests(float start, float end, String day, UserBean id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		EmployeeAvailabilityBean bean = new EmployeeAvailabilityBean(start, end, id, day);

		bean.setActive(0);
		try{
			tx = session.beginTransaction();
			session.save(bean);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return "failure";
		} finally {
			session.close();
		}

		return "success";
	}

	public List<EmployeeAvailabilityBean> getAvailableTimesByDay(Integer id, String day) {
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(EmployeeAvailabilityBean.class);
		List<EmployeeAvailabilityBean> list = crit.add(Restrictions.like("user.user_id", id))
				.add(Restrictions.like("day", day)).list();
		
		session.close();
		
		return list;
	}

	/**
	 * Returns a list of the times to the services that needs to be parsed into the
	 * proper JSON
	 */
	public List<EmployeeAvailabilityBean> getStartTimesByDay(String day) {
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(EmployeeAvailabilityBean.class);
		List<EmployeeAvailabilityBean> list = crit.add(Restrictions.like("active", 1))
												  .add(Restrictions.like("day", day)).list();
		
		session.close();
		
		return list;
	}

	public List getStartTimesById(Integer id) {
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(EmployeeAvailabilityBean.class);
		List<EmployeeAvailabilityBean> list = crit.add(Restrictions.like("active", 1))
				                                  .add(Restrictions.like("user.user_id", id)).list();
		
		session.close();
		
		return list;
	}

	public List getEmployeeAvailableForRange(Integer id, String weekday, float start, float end) {

		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(EmployeeAvailabilityBean.class);
		List<EmployeeAvailabilityBean> list = crit.add(Restrictions.like("user.user_id", id))
				.add(Restrictions.like("day", weekday)).add(Restrictions.le("starttime", start))
				.add(Restrictions.ge("endtime", end)).list();
		
		session.close();

		return list;

	}
	
	public List<ScheduleTimeBean> getScheduleByEmployee(Integer id, Timestamp startTime, Timestamp endTime) {
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(ScheduleTimeBean.class);
		List<ScheduleTimeBean> list = crit.add(Restrictions.like("users.user_id", id))
				.add(Restrictions.between("startTime", startTime, endTime)).list();
		Transaction tx = null;
		
		session.close();
		
		return list;
	}
	
	/**
	 * Sets default times
	 **/
	public String updateDefaultRequests(float start, float end, String day, UserBean id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		EmployeeAvailabilityBean bean = new EmployeeAvailabilityBean(start, end, id, day);

		bean.setActive(1);
		try{
			tx = session.beginTransaction();
			session.save(bean);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return "failure";
		} finally {
			session.close();
		}

		return "success";
	}
	
	/*=============================================================================================================
	 * 
	 * 							COORDINATOR METHODS FOR EMPLOYEE AVAILABILITY TABLE BELOW
	 * 
	 * =============================================================================================================*/

	/**
	 * Remove the old available shifts for an employee so the coordinator can approve the new one
	 **/
	public boolean removeAllReguestsCoor(Integer id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Query query = null;
		String hql = "FROM EmployeeAvailabilityBean WHERE active = 1 and user.user_id = :fart";
		try {
			tx = session.beginTransaction();
			query = session.createQuery(hql);
			query.setParameter("fart", id);
			List<EmployeeAvailabilityBean> list = query.list();
			for(EmployeeAvailabilityBean n : list) {
				session.delete(n);
			}
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			return false;
			}finally {
				session.close();
			}
		return true;
	}
	
	/**
	 * Remove pending available times when the coordinator denies the new availability hours
	 **/
	public boolean denyRequests(Integer id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Query query = null;
		String hql = "FROM EmployeeAvailabilityBean WHERE active = 0 and user.user_id = :fart";
		try {
			tx = session.beginTransaction();
			query = session.createQuery(hql);
			query.setParameter("fart", id);
			List<EmployeeAvailabilityBean> list = query.list();
			for(EmployeeAvailabilityBean n : list) {
				session.delete(n);
			}
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			return false;
			}finally {
				session.close();
			}
		return true;
	}
	
	public boolean approveNewAvail(Integer id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Query query = null;
		String hql = "UPDATE EmployeeAvailabilityBean SET active = 1 WHERE active = 0 and user.user_id = :fart";
		if(removeAllReguestsCoor(id)) {
			try {
				tx = session.beginTransaction();
				query = session.createQuery(hql);
				query.setParameter("fart", id);
				query.executeUpdate();
				tx.commit();
			}catch(HibernateException e) {
				e.printStackTrace();
				return false;
			}finally {
				session.close();
			}
		}
		else return false;
		
		return true;
	}
	
	public List getPendingTimesCoor(Integer id) {
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(EmployeeAvailabilityBean.class);
		List<EmployeeAvailabilityBean> list = crit.add(Restrictions.like("active", 0))
				                                  .add(Restrictions.like("user.user_id", id)).list();
		
		session.close();
		
		return list;
	}


}
