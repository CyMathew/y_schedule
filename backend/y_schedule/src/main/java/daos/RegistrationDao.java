package daos;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.User_TableBeans;
import util.HibernateUtil;

public class RegistrationDao {
	
	public String createNewEmployee(String username, String lastname, String password, String firstname) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		User_TableBeans newemployee = new User_TableBeans(firstname, lastname, password, username);
		try{
			tx = session.beginTransaction();
			session.save(newemployee);
			tx.commit();
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
			return "Failure";
		}finally{
			session.close();
		}
		return "Success";
	}
}
