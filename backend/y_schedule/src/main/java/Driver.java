import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.MessageListBean;
import beans.UserBean;
import daos.MessageDao;
import daos.UserDao;
import util.HibernateUtil;

public class Driver {
	
	public static void main(String[] args) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		long time = 0;
		try {
			date = dateFormat.parse("23/09/2007");
			time = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MessageListBean mlb= null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mlb =(MessageListBean)session.createQuery("FROM MessageListBean WHERE message_list_id = 2").uniqueResult();
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close(); //persons is now in the detached state.
}		UserDao ud = new UserDao();
		UserBean bean = new UserBean();
		bean = ud.getUserById(1);
		MessageDao MD = new MessageDao();
		System.out.println(MD.createNewMessage(bean, "TEST TEST TEST TEST", new Timestamp(time),mlb));
		System.exit(0);
	}

}
