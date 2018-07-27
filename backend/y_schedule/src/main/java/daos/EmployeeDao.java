package daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.EmployeeAvailabilityBean;
import util.HibernateUtil;

public class EmployeeDao {
	
	/**
	 * Removes old records of employee availability returns true if success false if fail
	 * */
	public boolean removeAllReguests(Integer id) {
		Session session = HibernateUtil.getSession();
		Query query1 = session.getNamedQuery("removeRequests");
		query1.setParameter("id", id);
		try {
			query1.list();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * returns a string if the table was successfully updated with the new availability times
	 * */
	public String updateRequests(String start,String end, Integer id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;		
		EmployeeAvailabilityBean bean = new EmployeeAvailabilityBean(start, end, id);
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
	
	public List<String> getStartTimesById(Integer id){
		List<String> stringlist = null;
		
		
		return stringlist;
	}
}
