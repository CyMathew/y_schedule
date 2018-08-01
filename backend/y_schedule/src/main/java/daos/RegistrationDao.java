package daos;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.UserBean;
import util.HibernateUtil;

public class RegistrationDao {
	
	public UserBean createNewEmployee(String username, String lastname, String password, String firstname, Integer storeid) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		UserBean newemployee = new UserBean(firstname, lastname, password, username);
		newemployee.setStoreId(storeid);
		try{
			tx = session.beginTransaction();
			session.save(newemployee);
			tx.commit();
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
		return newemployee;
	}
}
