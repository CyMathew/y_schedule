package daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.EmployeeAvailabilityBean;
import beans.UserBean;
import util.HibernateUtil;

public class EmployeeDao {
	
	/**
	 * Removes old records of employee availability returns true if success false if fail
	 * */
	public boolean removeAllReguests(Integer id) {
		Session session = HibernateUtil.getSession();
		Query query1 = session.getNamedQuery("getAvail");
		try {
			List req = query1.list();
			for(int i=0; i<= req.size(); i++) {
				if(((EmployeeAvailabilityBean)req.get(i)).getUser().getUser_id() == id) {
					session.delete((EmployeeAvailabilityBean)req.get(i));
					}
			}
		}catch(Exception e) {
			e.printStackTrace();
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
	public List getStartTimesByDay(String day){
		Session session = HibernateUtil.getSession();
		Query query = session.getNamedQuery("getTimes");
		query.setParameter("day", day);
		return query.list();
	}
	
	public List getStartTimesById(Integer id) {
		Session session = HibernateUtil.getSession();
		Query query = session.getNamedQuery("getAvail");
		query.setParameter("id", id);
		return query.list();
	}
}
