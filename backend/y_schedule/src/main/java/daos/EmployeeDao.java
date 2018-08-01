package daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import beans.EmployeeAvailabilityBean;
import beans.UserBean;
import util.HibernateUtil;

public class EmployeeDao {

	/**
	 * Removes old records of employee availability returns true if success false if fail
	 * */
	public boolean removeAllReguests(Integer id) {
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(EmployeeAvailabilityBean.class);
		List<EmployeeAvailabilityBean> list = crit.add(Restrictions.like("user.user_id", id)).list();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for(EmployeeAvailabilityBean i : list) {
				i.setActive(0);
				session.delete(i);
			}
			tx.commit();

		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		return true;
	}
	
	/**
	 * returns a string if the table was successfully updated with the new availability times
	 * */
	public String updateRequests(float start, float end, String day, UserBean id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;		
		EmployeeAvailabilityBean bean = new EmployeeAvailabilityBean(start, end, id, day);
		bean.setActive(0);
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
	
	/**
	 * Returns a list of the times to the services that needs to be parsed into the proper JSON
	 */
	public List<EmployeeAvailabilityBean> getStartTimesByDay(String day){
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(EmployeeAvailabilityBean.class);
		List<EmployeeAvailabilityBean> list = crit.add(Restrictions.like("active", 1))
												  .add(Restrictions.like("day", day)).list();
		return list;
	}
	
	public List getStartTimesById(Integer id) {
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(EmployeeAvailabilityBean.class);
		List<EmployeeAvailabilityBean> list = crit.add(Restrictions.like("active", 1))
				                                  .add(Restrictions.like("user.user_id", id)).list();
		return list;
	}

	public List getEmployeeAvailableForRange(Integer id, String weekday, float start, float end) {

		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(EmployeeAvailabilityBean.class);
		List<EmployeeAvailabilityBean> list = crit.add(Restrictions.like("user.user_id", id)).add(Restrictions.like("day", weekday))
				.add(Restrictions.le("starttime", start)).add(Restrictions.ge("endtime", end)).list();

		return list;
		
	}

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
	
	public boolean approveNewAvail(Integer id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Query query = null;
		String hql = "FROM EmployeeAvailabilityBean WHERE active = 0 and user.user_id = :fart";
		if(removeAllReguestsCoor(id)) {
			try {
				tx = session.beginTransaction();
				query = session.createQuery(hql);
				query.setParameter("fart", id);
				List<EmployeeAvailabilityBean> list = query.list();
				for(EmployeeAvailabilityBean n : list) {
					n.setActive(1);
					session.save(n);
				}
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

}
