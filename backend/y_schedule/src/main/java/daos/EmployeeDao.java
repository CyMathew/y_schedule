package daos;

import java.util.List;

import org.apache.log4j.Logger;
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
	public String updateRequests(String start,String end, String day, UserBean id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;		
		EmployeeAvailabilityBean bean = new EmployeeAvailabilityBean(start, end, id, day);
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
		List<EmployeeAvailabilityBean> list = crit.add(Restrictions.like("day", day)).list();
		return list;
	}
	
	public List getStartTimesById(Integer id) {
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(EmployeeAvailabilityBean.class);
		List<EmployeeAvailabilityBean> list = crit.add(Restrictions.like("user.user_id", id)).list();
		return list;
	}
}
